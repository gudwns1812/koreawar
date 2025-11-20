package dev.hjp.koreawargame.domain.domaindata.war

import androidx.compose.ui.graphics.Color
import dev.hjp.koreawargame.ui.theme.Apricot
import dev.hjp.koreawargame.ui.theme.MintGreen
import dev.hjp.koreawargame.ui.theme.NeonGreen
import dev.hjp.koreawargame.ui.theme.Orange
import dev.hjp.koreawargame.ui.theme.Pink40
import dev.hjp.koreawargame.ui.theme.Purple40
import dev.hjp.koreawargame.ui.theme.SkyBlue

data class Country(
    val countryName: String,
    val cities: List<BattleCity>,
    val color: Color = Color.Yellow
)

val jeollaCountry = Country(
    "전라", listOf(
        BattleCity(1, "해남", 200L),
        BattleCity(2, "여수", 500L),
        BattleCity(3, "목포", 1_000L, isCapital = true),
    )
)

val gwangjuCountry = Country(
    "광주", listOf(
        BattleCity(4, "남원", 2_000L),
        BattleCity(5, "전주", 3_200L),
        BattleCity(6, "광주", 4_500L, isCapital = true),
    ),
    color = Color.Cyan
)

val busanCountry = Country(
    "부산", listOf(
        BattleCity(7, "진주", 5_800L),
        BattleCity(8, "합천", 7_500L),
        BattleCity(9, "울산", 9_000L),
        BattleCity(10, "부산", 11_000L, isCapital = true),
    ),
    color = Pink40
)

val chungcheongCountry = Country(
    "충청",
    listOf(
        BattleCity(11, "공주", 15_000L),
        BattleCity(12, "서산", 20_000L, isCapital = true),
    ),
    color = Orange
)

val gyeongsangCountry = Country(
    "경상", listOf(
        BattleCity(13, "포항", 28_000L),
        BattleCity(14, "울진", 40_000L),
        BattleCity(15, "안동", 52_000L),
        BattleCity(16, "김천", 65_000L),
        BattleCity(17, "대구", 80_000L, isCapital = true),
    ),
    color = NeonGreen
)

val daejeonCountry = Country(
    "대전", listOf(
        BattleCity(18, "무주", 100_000L),
        BattleCity(19, "원주", 125_000L),
        BattleCity(20, "청주", 150_000L),
        BattleCity(21, "대전", 180_000L, isCapital = true),
    ),
    color = Color.Blue
)

val gangwonCountry = Country(
    "강원", listOf(
        BattleCity(22, "평창", 215_000L),
        BattleCity(23, "강릉", 250_000L),
        BattleCity(24, "인제", 290_000L),
        BattleCity(25, "속초", 335_000L),
        BattleCity(26, "춘천", 390_000L, isCapital = true),
    ),
    color = Color.DarkGray
)

val wonsanCountry = Country(
    "원산", listOf(
        BattleCity(27, "고성", 450_000L),
        BattleCity(28, "철원", 520_000L),
        BattleCity(29, "통천", 600_000L),
        BattleCity(30, "신평", 690_000L),
        BattleCity(31, "원산", 850_000L, isCapital = true),
    ),
    color = Purple40
)

val pyeongyangCountry = Country(
    "평양", listOf(
        BattleCity(32, "곡산", 950_000L),
        BattleCity(33, "순천", 1_075_000L),
        BattleCity(34, "구성", 1_225_000L),
        BattleCity(35, "남포", 1_400_000L),
        BattleCity(36, "평양", 1_600_000L, isCapital = true),
    ),
    color = MintGreen
)

val pyeonganCountry = Country(
    "평안", listOf(
        BattleCity(37, "운산", 1_800_000L),
        BattleCity(38, "초산", 2_100_000L),
        BattleCity(39, "만포", 2_400_000L),
        BattleCity(40, "벽동", 2_750_000L),
        BattleCity(41, "신의주", 3_250_000L, isCapital = true),
    ),
    color = Color.White
)

val hamheungCountry = Country(
    "함흥",
    listOf(
        BattleCity(42, "맹산", 3_800_000L),
        BattleCity(43, "장진", 4_400_000L),
        BattleCity(44, "강계", 5_000_000L),
        BattleCity(45, "낭림", 5_700_000L),
        BattleCity(46, "자성", 6_450_000L),
        BattleCity(47, "갑산", 7_200_000L),
        BattleCity(48, "함흥", 8_200_000L, isCapital = true),
    ),
    color = SkyBlue
)

val hamgyeongCountry = Country(
    "함경", listOf(
        BattleCity(49, "단천", 9_200_000L),
        BattleCity(50, "혜산", 10_300_000L),
        BattleCity(51, "백암", 11_500_000L),
        BattleCity(52, "삼지연", 12_800_000L),
        BattleCity(53, "나선", 14_300_000L),
        BattleCity(54, "청진", 17_000_000L, isCapital = true),
    ),
    color = Apricot
)

val northkoreaCountry = Country(
    "북한", listOf(
        BattleCity(55, "사리원", 20_000_000L, isCapital = true),
        BattleCity(56, "해주", 24_000_000L, isCapital = true),
        BattleCity(57, "연천", 29_000_000L, isCapital = true),
        BattleCity(58, "개성", 35_000_000L, isCapital = true),
        BattleCity(59, "이천", 42_000_000L, isCapital = true),
        BattleCity(60, "용인", 50_000_000L, isCapital = true),
        BattleCity(61, "수원", 59_000_000L, isCapital = true),
        BattleCity(62, "인천", 69_000_000L, isCapital = true),
        BattleCity(63, "서울", 90_000_000L, isCapital = true),
    ),
    color = Color.Red
)

val initialCountries: List<Country> = listOf(
    jeollaCountry,
    gwangjuCountry,
    busanCountry,
    chungcheongCountry,
    gyeongsangCountry,
    daejeonCountry,
    gangwonCountry,
    wonsanCountry,
    pyeongyangCountry,
    pyeonganCountry,
    hamheungCountry,
    hamgyeongCountry,
    northkoreaCountry
)