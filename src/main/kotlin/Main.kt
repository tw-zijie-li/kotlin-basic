import com.thoughtworks.kotlin_basic.product.adapter.ExternalJsonService
import com.thoughtworks.kotlin_basic.product.adapter.HttpClient
import com.thoughtworks.kotlin_basic.product.adapter.InventoryAdapter
import com.thoughtworks.kotlin_basic.product.adapter.ProductAdapter
import com.thoughtworks.kotlin_basic.product.service.ProductQueryService
import com.thoughtworks.kotlin_basic.util.GsonUtil
import kotlinx.cli.ArgParser
import kotlinx.cli.ExperimentalCli
import kotlinx.cli.Subcommand

@OptIn(ExperimentalCli::class)
fun main(args: Array<String>) {
    val parser = ArgParser("Product information list")
    val showProduct = ShowProductInfo()
    parser.subcommands(showProduct)
    parser.parse(args)

}


@OptIn(ExperimentalCli::class)
class ShowProductInfo : Subcommand("list-product", "list all products information") {

    private val queryService: ProductQueryService

    init {
        val externalJsonService = HttpClient.getService(ExternalJsonService::class)
        val inventoryAdapter = InventoryAdapter(externalJsonService)
        val productAdapter = ProductAdapter(externalJsonService)
        queryService = ProductQueryService(productAdapter, inventoryAdapter)
    }


    override fun execute() {
        val result = queryService.listAggregateProductInfo()
        println(GsonUtil.toJson(result))
    }
}