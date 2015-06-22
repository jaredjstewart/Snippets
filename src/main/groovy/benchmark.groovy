import com.jaredjstewart.parser.IntranetParser
import com.jaredjstewart.resource_loading.ResourceLoader

String allEmployeesHTML = new File(ResourceLoader.loadResourceAsURI('AllEmployees.html')).text



def r = benchmark {        // use 'new groovyx.gbench.BenchmarkBuilder().run {' in Groovy 1.8 or earlier.
    'jsoup' {
    IntranetParser.parseEmployeesUsingJsoup(allEmployeesHTML)

    }
    'tagsoup' {
    IntranetParser.parseEmployeesFromHtml(allEmployeesHTML)
    }
}

r.prettyPrint()