package rooit.me.xo.utils.compose

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
public fun SpacerEx(size: Int): Unit = Spacer(Modifier.size(size.dp))