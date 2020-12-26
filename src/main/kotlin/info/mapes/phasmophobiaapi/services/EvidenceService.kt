package info.mapes.phasmophobiaapi.services

import info.mapes.phasmophobiaapi.domains.Ghost
import info.mapes.phasmophobiaapi.domains.GhostEvidence
import io.micronaut.http.HttpStatus
import io.micronaut.http.exceptions.HttpStatusException
import javax.inject.Singleton

@Singleton
class EvidenceService {

    fun getAllGhosts(): List<Ghost> {
        return Ghost.ALL_GHOSTS
    }

    fun getEvidenceOptions(): Array<GhostEvidence> {
        return GhostEvidence.values()
    }

    fun calculateOptions(foundEvidence: List<String>): List<Ghost> {
        val mappedOptions = foundEvidence.mapNotNull { GhostEvidence.fromString(it) }

        if (foundEvidence.size != mappedOptions.size) {
            throw HttpStatusException(HttpStatus.BAD_REQUEST, "At least one evidence type is not valid!")
        }

        return Ghost.ALL_GHOSTS.filter { it.evidenceOptions.containsAll(mappedOptions) }
    }

}