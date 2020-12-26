package info.mapes.phasmophobiaapi.controllers.v1

import info.mapes.phasmophobiaapi.domains.Ghost
import info.mapes.phasmophobiaapi.domains.GhostEvidence
import info.mapes.phasmophobiaapi.services.EvidenceService
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.validation.Validated
import javax.validation.constraints.Size

@Controller(value = "/phasmophobia_ghosts/v1", produces = [MediaType.APPLICATION_JSON], consumes = [MediaType.APPLICATION_JSON])
@Validated
class GhostController(private val evidenceService: EvidenceService) {

    @Get("/")
    fun getAllGhosts(): List<Ghost> = evidenceService.getAllGhosts()

    @Get("/evidence_options")
    fun getEvidenceOptions(): Array<GhostEvidence> = evidenceService.getEvidenceOptions()

    @Post("calculate_options")
    fun calculateOptions(@Body @Size(min = 1, max = 3) foundEvidence: List<String>): List<Ghost> {
        return evidenceService.calculateOptions(foundEvidence)
    }
}