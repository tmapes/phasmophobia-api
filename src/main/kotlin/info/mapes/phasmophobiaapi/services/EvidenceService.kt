package info.mapes.phasmophobiaapi.services

import info.mapes.phasmophobiaapi.configs.Evidence
import info.mapes.phasmophobiaapi.configs.Ghost
import info.mapes.phasmophobiaapi.configs.PhasmophobiaProperties
import info.mapes.phasmophobiaapi.domains.CalculatedEvidenceResponse
import info.mapes.phasmophobiaapi.utils.filterToSet
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

        val matchedGhosts = phasmophobiaProperties.ghosts
            .filterToSet {
                it.evidenceTypes.map { evidence ->
                    evidence.code
                }.containsAll(foundEvidence)
            }

        if (matchedGhosts.isEmpty()) {
            throw HttpStatusException(
                HttpStatus.BAD_REQUEST,
                "$foundEvidence is not valid for any ghosts!"
            )
        }

        val remainingEvidence = matchedGhosts
            .flatMap { it.evidenceCodes }
            .filterToSet { it !in foundEvidence }

        return CalculatedEvidenceResponse(
            remainingEvidence = remainingEvidence,
            matchedGhosts = matchedGhosts,
            foundEvidence = foundEvidence,
        )
    }

}
