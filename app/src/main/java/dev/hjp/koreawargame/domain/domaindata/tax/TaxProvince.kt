sealed interface TaxProvince {
    val provinceName: String
    val taxRate: Int
}

enum class SouthKoreaTaxProvince(
    override val provinceName: String,
    override val taxRate: Int,
) : TaxProvince {
    JEJU("제주", 7),
    JEOLLA("전라", 8),
    GYEONGSANG("경상", 15),
    CHUNGCHEONG("충청", 20),
    GANGWON("강원", 25),
    GYEONGGI("경기", 45);
}

enum class NorthKoreaTaxProvince(
    override val provinceName: String,
    override val taxRate: Int,
) : TaxProvince {
    HWANGHAE("황해", 35),
    WONSAN("원산", 25),
    PYEONGAN("평안", 30),
    HAMGYEONG("함경", 20)
}