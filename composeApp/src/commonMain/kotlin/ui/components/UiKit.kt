package ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DisplayText(label: String, value: String){
    Text(
        fontSize = 12.sp,
        fontStyle = FontStyle.Italic,
        text = label,
    )
    Text(
        fontSize = 14.sp,
        fontStyle = FontStyle.Italic,
        text = value,
    )
    Spacer(modifier = Modifier.height(16.dp))
}
