package info.mapes.phasmophobiaapi.domains

data class CalculatedEvidenceResponse(
    val remainingEvidence: Set<String>,
    val matchedGhosts: Set<Any>,
    val foundEvidence: Set<String>,
)
