package pe.devpicon.android.mytestinglabapp.currencies

import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_currency.*
import pe.devpicon.android.mytestinglabapp.R
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.util.*

class CurrencyActivity : AppCompatActivity() {

    val TAG = this@CurrencyActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currency)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val locales = resources.configuration.locales

            tv_current_locale.text = getString(R.string.label_current_primary_locale, locales[0].toLanguageTag())
            val currency = android.icu.util.Currency.getInstance(locales[0])
            tv_current_currency_code.text = getString(R.string.label_current_primary_currency, currency.currencyCode)
            tv_current_currency_symbol.text = getString(R.string.label_current_primary_currency_symbol, currency.symbol)
            tv_formatted_number.text = "Current backend Currency code: MXN \n" +
                    "1000.00f is formatted as ${formatCurrency(1000.00f, "MXN", locales[0])}\n" +
                    "1000.213354f is formatted as ${formatCurrency(1000.213354f, "MXN", locales[0])}\n" +
                    "1000.6789f is formatted as ${formatCurrency(1000.6789f, "MXN", locales[0])}\n\n" +
                    "Current backend Currency code: CLP \n" +
                    "1000.00f is formatted as ${formatCurrency(1000.00f, "CLP", locales[0])}\n" +
                    "1000.213354f is formatted as ${formatCurrency(1000.213354f, "CLP", locales[0])}\n" +
                    "1000.6789f is formatted as ${formatCurrency(1000.6789f, "CLP", locales[0])}\n\n"

        } else {
            val locale = resources.configuration.locale
            tv_current_locale.text = getString(R.string.label_current_primary_locale, locale.toString())
            val currency = java.util.Currency.getInstance(locale)
            tv_current_currency_code.text = getString(R.string.label_current_primary_currency, currency.currencyCode)
            tv_current_currency_symbol.text = getString(R.string.label_current_primary_currency_symbol, currency.symbol)
            tv_formatted_number.text = "Current backend Currency code: MXN \n" +
                    "1000.00f is formatted as ${formatCurrency(1000.00f, "MXN", locale)}\n" +
                    "1000.213354f is formatted as ${formatCurrency(1000.213354f, "MXN", locale)}\n" +
                    "1000.6789f is formatted as ${formatCurrency(1000.6789f, "MXN", locale)}\n\n" +
                    "Current backend Currency code: CLP \n" +
                    "1000.00f is formatted as ${formatCurrency(1000.00f, "CLP", locale)}\n" +
                    "1000.213354f is formatted as ${formatCurrency(1000.213354f, "CLP", locale)}\n" +
                    "1000.6789f is formatted as ${formatCurrency(1000.6789f, "CLP", locale)}\n"
        }

        execute()

    }

    fun formatCurrency(currencyValue: Float, currencyCode: String, currentLocale: Locale): String {
        val currency = java.util.Currency.getInstance(currencyCode)
        val format = java.text.NumberFormat.getCurrencyInstance(currentLocale)
        format.currency = currency
        format.maximumFractionDigits = currency.defaultFractionDigits
        format.minimumFractionDigits = currency.defaultFractionDigits

        return format.format(currencyValue)
    }

    fun execute() {

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
        Log.d(TAG, bizarre)

        val locales = arrayOf(Locale("en", "US"), Locale("de", "DE"), Locale("fr", "FR"), resources.configuration.locale)

        for (i in locales.indices) {
            localizedFormat("###,###.###", 123456.789, locales[i])
        }
    }


    fun customFormat(pattern: String, value: Double) {
        val myFormatter = DecimalFormat(pattern)
        val output = myFormatter.format(value)
        Log.d(TAG, value.toString() + "  " + pattern + "  " + output)
    }

    fun localizedFormat(pattern: String, value: Double,
                        loc: Locale) {
        val currency = java.util.Currency.getInstance("MXN")
        val format = java.text.NumberFormat.getCurrencyInstance(loc)
        format.currency = currency
        val formattedOutput = format.format(value)
        Log.d(TAG, currency.currencyCode + "  '" + formattedOutput + "'  " + loc.toString())


        val nf = NumberFormat.getNumberInstance(loc)
        val ci = NumberFormat.getCurrencyInstance(loc)
        val df = ci as DecimalFormat
        df.applyPattern(pattern)
        val output = df.format(value)
        Log.d(TAG, pattern + "  " + output + "  " + loc.toString())
    }

}
