package musicplayer.presentation.utils

import android.content.Context

fun Context.resourceNameToRawRes(resourceName: String): Int? =
    resources.getIdentifier(resourceName, "raw", this.packageName)