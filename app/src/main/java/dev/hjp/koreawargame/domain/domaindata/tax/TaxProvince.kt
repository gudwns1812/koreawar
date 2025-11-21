sealed interface TaxProvince {
    val provinceName: String
    val taxRate: Int
    val unlockAt: Int
}

enum class SouthKoreaTaxProvince(
    override val provinceName: String,
    override val taxRate: Int,
    override val unlockAt: Int = 0
) : TaxProvince {
    JEJU("제주", 7),
    JEOLLA("전라", 8, 3),
    GYEONGSANG("경상", 15, 9),
    CHUNGCHEONG("충청", 20, 13),
    GANGWON("강원", 25, 24),
    GYEONGGI("경기", 45, 52);
}

enum class NorthKoreaTaxProvince(
    override val provinceName: String,
    override val taxRate: Int,
    override val unlockAt: Int = 0,
) : TaxProvince {
    HWANGHAE("황해", 35, 50),
    WONSAN("원산", 25, 29),
    PYEONGAN("평안", 30, 34),
    HAMGYEONG("함경", 20, 44)
}