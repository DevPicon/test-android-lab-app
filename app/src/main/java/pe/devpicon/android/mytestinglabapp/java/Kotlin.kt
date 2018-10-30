package pe.devpicon.android.mytestinglabapp.java

class Kotlin {

    fun main(args: Array<String>) {
        load2 {value:String -> println(value)}
    }

    private fun load2(method: (String) -> Unit){
        method("Hello Kotlin")
    }
}
