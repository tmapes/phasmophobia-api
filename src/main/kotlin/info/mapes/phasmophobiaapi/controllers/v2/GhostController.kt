package info.mapes.phasmophobiaapi.controllers.v2

import info.mapes.phasmophobiaapi.configs.Evidence
import info.mapes.phasmophobiaapi.configs.Ghost
import info.mapes.phasmophobiaapi.domains.CalculatedEvidenceResponse
import info.mapes.phasmophobiaapi.services.EvidenceService
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.validation.Validated
import javax.validation.constraints.Size

@Controller(
    value = "/phasmophobia_ghosts/v2",
    produces = [MediaType.APPLICATION_JSON],
    consumes = [MediaType.APPLICATION_JSON]
)
@Validated
class GhostController(private val evidenceService: EvidenceService) {

    @Get("/")
    fun getAllGhosts(): List<Ghost> = evidenceService.getGhosts()

    @Get("/evidence_options")
    fun getEvidenceOptions(): Set<Evidence> = evidenceService.getEvidenceOptionsV2()

    @Post("calculate_options")
    fun calculateOptions(@Body @Size(min = 1, max = 3) foundEvidence: Set<String>): CalculatedEvidenceResponse {
        return evidenceService.calculateOptions(foundEvidence)
    }
}
