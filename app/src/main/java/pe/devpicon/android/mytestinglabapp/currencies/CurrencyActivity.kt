package pe.devpicon.android.mytestinglabapp.currencies

import android.icu.util.Currency
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
            tv_formatted_number.text = getString(R.string.label_formatted_number, NumberFormat.getCurrencyInstance(locales[0]).format(100.00))
        } else {
            val locale = resources.configuration.locale
            tv_current_locale.text = getString(R.string.label_current_primary_locale, locale.toString())
            val currency = java.util.Currency.getInstance(locale)
            tv_current_currency_code.text = getString(R.string.label_current_primary_currency, currency.currencyCode)
            tv_current_currency_symbol.text = getString(R.string.label_current_primary_currency_symbol, currency.symbol)
            tv_formatted_number.text = getString(R.string.label_formatted_number, NumberFormat.getCurrencyInstance(locale).format(100.00))
        }

        execute()

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
