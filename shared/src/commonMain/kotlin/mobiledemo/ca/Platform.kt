package mobiledemo.ca

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform