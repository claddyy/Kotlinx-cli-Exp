import kotlinx.cli.*
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import java.lang.Exception

suspend fun main(args: Array<String>) {
    val parser = ArgParser("cli-tool")
    val startHeight by parser.option(ArgType.Int, shortName = "s", description = "The start height").required()

    parser.parse(args)

    try {
        val blockIds = getBlockIds(startHeight)
        val txIds = getTxIds(blockIds.id)
        val txIdsJson = Json.encodeToString(TxIds(txIds))
        println(txIdsJson)
    } catch (e: Exception) {
        println("Error: ${e.message}")
    }
}