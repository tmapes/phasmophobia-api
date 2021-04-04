package info.mapes.phasmophobiaapi.services

import info.mapes.phasmophobiaapi.configs.Evidence
import info.mapes.phasmophobiaapi.configs.Ghost
import info.mapes.phasmophobiaapi.configs.PhasmophobiaProperties
import info.mapes.phasmophobiaapi.domains.CalculatedEvidenceResponse
import info.mapes.phasmophobiaapi.domains.Ghost.Companion.ALL_GHOSTS
import info.mapes.phasmophobiaapi.domains.GhostEvidence
import io.micronaut.http.HttpStatus
import io.micronaut.http.exceptions.HttpStatusException
import javax.inject.Singleton

@Singleton
class EvidenceService(
    private val phasmophobiaProperties: PhasmophobiaProperties
) {

    fun getGhosts(): List<Ghost> {
        return phasmophobiaProperties.ghosts
    }

    fun getEvidenceOptionsV2(): Set<Evidence> = phasmophobiaProperties.evidenceTypes.toHashSet()

    fun calculateOptions(foundEvidence: Set<String>): CalculatedEvidenceResponse {
        val evidenceValid = foundEvidence.all { phasmophobiaProperties.evidenceMap.contains(it) }
        if (!evidenceValid) {
            throw HttpStatusException(
                HttpStatus.BAD_REQUEST,
                "At least one of the given evidence options are not valid! $foundEvidence"
            )
        }

        val matchedGhosts = phasmophobiaProperties.ghosts.filter { it.evidenceCodes.containsAll(foundEvidence) }.toSet()

        if (matchedGhosts.isEmpty()) {
            throw HttpStatusException(
                HttpStatus.BAD_REQUEST,
                "$foundEvidence is not valid for any ghosts!"
            )
        }

        val remainingEvidence = matchedGhosts.flatMap { it.evidenceCodes }.filterNot { it in foundEvidence }.toSet()

        return CalculatedEvidenceResponse(
            remainingEvidence = remainingEvidence,
            matchedGhosts = matchedGhosts,
            foundEvidence = foundEvidence,
        )
    }

    @Deprecated(
        DEPRECATION_MESSAGE,
        ReplaceWith("ALL_GHOSTS", "info.mapes.phasmophobiaapi.domains.Ghost.Companion.ALL_GHOSTS")
    )
    fun getAllGhosts(): List<info.mapes.phasmophobiaapi.domains.Ghost> {
        return ALL_GHOSTS
    }

    @Deprecated(
        DEPRECATION_MESSAGE,
        ReplaceWith("GhostEvidence.values()", "info.mapes.phasmophobiaapi.domains.GhostEvidence")
    )
    fun getEvidenceOptions(): Array<GhostEvidence> {
        return GhostEvidence.values()
    }

    @Deprecated(DEPRECATION_MESSAGE)
    fun calculateOptions(foundEvidence: List<String>): List<info.mapes.phasmophobiaapi.domains.Ghost> {
        val mappedOptions = foundEvidence.mapNotNull { GhostEvidence.fromString(it) }

        if (foundEvidence.size != mappedOptions.size) {
            throw HttpStatusException(HttpStatus.BAD_REQUEST, "At least one evidence type is not valid!")
        }

        return ALL_GHOSTS.filter { it.evidenceOptions.containsAll(mappedOptions) }
    }

    companion object {
        const val DEPRECATION_MESSAGE = "v1 function, use new function that deals with YAML config ghosts"
    }
}
