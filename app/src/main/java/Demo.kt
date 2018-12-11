
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.util.*

fun customFormat(pattern: String, value: Double) {
    val myFormatter = DecimalFormat(pattern)
    val output = myFormatter.format(value)
    println(value.toString() + "  " + pattern + "  " + output)
}

fun localizedFormat(pattern: String, value: Double,
                    loc: Locale) {
    val nf = NumberFormat.getNumberInstance(loc)
    val df = nf as DecimalFormat
    df.applyPattern(pattern)
    val output = df.format(value)
    println(pattern + "  " + output + "  " + loc.toString())
}


fun main(args: Array<String>) {

    customFormat("###,###.###", 123456.789)
    customFormat("###.##", 123456.789)
    customFormat("000000.000", 123.78)
    customFormat("$###,###.###", 12345.67)
    customFormat("\u00a5###,###.###", 12345.67)

    val currentLocale = Locale("en", "US")

    val unusualSymbols = DecimalFormatSymbols(currentLocale)
    unusualSymbols.decimalSeparator = '|'
    unusualSymbols.groupingSeparator = '^'
    val strange = "#,##0.###"
    val weirdFormatter = DecimalFormat(strange, unusualSymbols)
    weirdFormatter.groupingSize = 4
    val bizarre = weirdFormatter.format(12345.678)
    println(bizarre)

    val locales = arrayOf(Locale("en", "US"), Locale("de", "DE"), Locale("fr", "FR"))

    for (i in locales.indices) {
        localizedFormat("###,###.###", 123456.789, locales[i])
    }

}
