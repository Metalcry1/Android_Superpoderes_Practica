import com.example.android_superpoderes_practica.Domain.Model.Extensions
import com.example.android_superpoderes_practica.Domain.Model.Hero
import com.example.android_superpoderes_practica.Domain.Model.Thumbnail
import com.example.android_superpoderes_practica.Domain.Model.Comics
import com.example.android_superpoderes_practica.Domain.Model.ComicsItem
import com.example.android_superpoderes_practica.dataa.mappers.HerosMappers.HeroToHeroRemoteDetail
import org.junit.Assert.assertEquals
import org.junit.Test

class HeroToHeroRemoteDetailTest {

    private val heroToHeroRemoteDetail = HeroToHeroRemoteDetail()

    @Test
    fun `map Hero to HeroRemoteDetail`() {
        // Given
        val hero = Hero(
            id = 123,
            name = "Iron Man",
            modified = "2022-02-10T15:00:00",
            thumbnail = Thumbnail("http://example.com/image", Extensions.jpg),
            comics = Comics("5", "collect", listOf(ComicsItem("resor", "item"))),
            series = Comics("5", "collect", listOf(ComicsItem("resor", "item"))),
            favourite = false
        )

        // When
        val result = heroToHeroRemoteDetail.map(hero)

        // Then
        assertEquals(hero.id, result.id)
        assertEquals(hero.name, result.name)
        assertEquals(hero.modified, result.modified)
        assertEquals(hero.comics, result.comics)
        assertEquals(hero.series, result.series)
    }
}
