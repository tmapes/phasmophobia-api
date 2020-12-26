package info.mapes.phasmophobiaapi.domains

import info.mapes.phasmophobiaapi.domains.GhostEvidence.*
import info.mapes.phasmophobiaapi.domains.GhostType.*

data class Ghost(
        val ghostType: GhostType,
        val evidenceOptions: List<GhostEvidence>
) {

    companion object {
        private val Spirit = Ghost(SPIRIT, listOf(SPIRIT_BOX, GHOST_WRITING, FINGERPRINTS))
        private val Poltergeist = Ghost(POLTERGEIST, listOf(GHOST_ORBS, SPIRIT_BOX, FINGERPRINTS))
        private val Oni = Ghost(ONI, listOf(EMF_LEVEL_FIVE, SPIRIT_BOX, GHOST_WRITING))
        private val Shade = Ghost(SHADE, listOf(EMF_LEVEL_FIVE, GHOST_ORBS, GHOST_WRITING))
        private val Jinn = Ghost(JINN, listOf(EMF_LEVEL_FIVE, GHOST_ORBS, SPIRIT_BOX))
        private val Wraith = Ghost(WRAITH, listOf(FREEZING_TEMPERATURES, SPIRIT_BOX, FINGERPRINTS))
        private val Demon = Ghost(DEMON, listOf(FREEZING_TEMPERATURES, SPIRIT_BOX, GHOST_WRITING))
        private val Yurei = Ghost(YUREI, listOf(FREEZING_TEMPERATURES, GHOST_ORBS, GHOST_WRITING))
        private val Mare = Ghost(MARE, listOf(FREEZING_TEMPERATURES, GHOST_ORBS, SPIRIT_BOX))
        private val Banshee = Ghost(BANSHEE, listOf(FREEZING_TEMPERATURES, EMF_LEVEL_FIVE, FINGERPRINTS))
        private val Phantom = Ghost(PHANTOM, listOf(FREEZING_TEMPERATURES, EMF_LEVEL_FIVE, GHOST_ORBS))
        private val Revenant = Ghost(REVENANT, listOf(EMF_LEVEL_FIVE, GHOST_WRITING, FINGERPRINTS))

        val ALL_GHOSTS = listOf(Spirit, Poltergeist, Oni, Shade, Jinn, Wraith, Demon, Yurei, Mare, Banshee, Phantom, Revenant)
    }
}

enum class GhostType {
    SPIRIT, WRAITH, PHANTOM,
    DEMON, POLTERGEIST, BANSHEE,
    JINN, MARE, REVENANT,
    SHADE, YUREI, ONI
}

enum class GhostEvidence {
    SPIRIT_BOX, FINGERPRINTS, GHOST_WRITING,
    FREEZING_TEMPERATURES, EMF_LEVEL_FIVE, GHOST_ORBS;

    companion object {

        private val evidenceOptions: Map<String, GhostEvidence> = mapOf(
                SPIRIT_BOX.name to SPIRIT_BOX,
                FINGERPRINTS.name to FINGERPRINTS,
                GHOST_WRITING.name to GHOST_WRITING,
                FREEZING_TEMPERATURES.name to FREEZING_TEMPERATURES,
                EMF_LEVEL_FIVE.name to EMF_LEVEL_FIVE,
                GHOST_ORBS.name to GHOST_ORBS
        )

        @JvmStatic
        fun fromString(input: String): GhostEvidence? {
            return evidenceOptions[input]
        }
    }
}