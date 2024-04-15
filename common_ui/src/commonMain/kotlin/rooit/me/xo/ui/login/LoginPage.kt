package rooit.me.xo.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import moe.tlaster.precompose.viewmodel.viewModel
import rooit.me.xo.utils.compose.SpacerEx

@Composable
fun LoginPage() {
    val viewModel = viewModel(LoginViewModel::class) {
        LoginViewModel()
    }

    Surface {
        Box(modifier = Modifier.fillMaxSize()) {

            LoginContentView(Modifier.fillMaxSize(), viewModel)
        }
    }
}

@Composable
private fun LoginContentView(modifier: Modifier, viewModel: LoginViewModel) {
    Column(modifier = modifier.padding(25.dp, 75.dp, 25.dp, 20.dp)) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "手機號登入",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
            )

            Text(
                text = "遊客登入",
                color = Color(0xFFFFFC00),
                fontSize = 15.sp,
                modifier = Modifier.clickable(true) {

                }.align(Alignment.BottomEnd)
            )
        }
        SpacerEx(15)
        Text("erferfwerfwer", color = Color.White, fontSize = 12.sp)
        SpacerEx(60)
        Box(modifier = Modifier.padding(24.dp, 0.dp, 0.dp, 0.dp)) {
            Text(
                text = "地區",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
            )
            Row(modifier = Modifier.clickable(true) {
                //select country
            }.align(Alignment.BottomEnd)) {

            }
        }
        SpacerEx(15)

        var phoneNumber by remember { mutableStateOf(TextFieldValue()) }
        var verifyCode by remember { mutableStateOf(TextFieldValue()) }
        var isButtonEnable by remember { mutableStateOf(false) }

        TextField(
            value = phoneNumber,
            singleLine = true,
            onValueChange = {
                phoneNumber = it
                isButtonEnable = phoneNumber.text.isNotEmpty() && verifyCode.text.isNotEmpty()
            },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth().background(
                Color(0x4d000000), shape = RoundedCornerShape(25.dp)
            ),
            placeholder = {
                Text("phone hint ", color = Color(0xb3ffffff))
            },
            trailingIcon = {

            },
            shape = RoundedCornerShape(25.dp),
            textStyle = TextStyle(color = Color.White, fontSize = 16.sp),
//            colors = defaultInputFieldColor()
        )

        SpacerEx(10)

        TextField(
            value = verifyCode,
            singleLine = true,
            onValueChange = {
                verifyCode = it
                isButtonEnable = phoneNumber.text.isNotEmpty() && verifyCode.text.isNotEmpty()
            },
            modifier = Modifier.fillMaxWidth().background(
                Color(0x4d000000), shape = RoundedCornerShape(25.dp)
            ),
            placeholder = {
                Text("verify_code", color = Color(0xb3ffffff))
            },
            trailingIcon = {
                Text(
                   " send_code",
                    color = Color.White,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(0.dp, 0.dp, 15.dp, 0.dp)
                )
            },
            shape = RoundedCornerShape(25.dp),
            textStyle = TextStyle(color = Color.White, fontSize = 16.sp),
//            colors = defaultInputFieldColor()
        )


        SpacerEx(15)

        val bgModifier = if (isButtonEnable) {
            Modifier.background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color(0xfffe278a), Color(0xffff87e7)
                    )
                ), shape = RoundedCornerShape(25.dp)
            )
        } else {
            Modifier.background(
                shape = RoundedCornerShape(25.dp), color = Color(0xcc8c8c8c)
            )
        }
        Box(modifier = bgModifier.fillMaxWidth().clickable(true) {
            if (isButtonEnable) {
                viewModel.login(phoneNumber.text, verifyCode.text)
            }
        }.height(50.dp), contentAlignment = Alignment.Center, content = {
            Text(
                text = "login",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        })

        SpacerEx(20)

        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Text(buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontSize = 14.sp, color = Color.White
                    )
                ) {
                    append("login_question")
                }
                withStyle(
                    style = SpanStyle(
                        fontSize = 14.sp,
                        color = Color(0xFFFFFC00),
                    )
                ) {
                    append("login_faq")
                }
            })
        }

        Box(
            modifier = Modifier.weight(1f).fillMaxWidth(),
            contentAlignment = Alignment.BottomCenter,
            content = {
                Text(
                    "version_code" + ":" + "getVersion()",
                    color = Color.White,
                    fontSize = 12.sp
                )
            })
    }
}
