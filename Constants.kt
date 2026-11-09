/*
 * Copyright 2026 Morphe.
 * Patches for TimeTree (works.jubilee.timetree)
 */

package app.morphe.patches.timetree.shared

import app.morphe.patcher.patch.Compatibility

object Constants {
    const val PACKAGE_TIMETREE = "works.jubilee.timetree"

    val COMPATIBILITY_TIMETREE = Compatibility(
        packageName = PACKAGE_TIMETREE,
        versions = listOf("15.34.1")
    )
}
