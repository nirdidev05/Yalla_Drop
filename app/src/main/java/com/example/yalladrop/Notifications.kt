package com.example.yalladrop


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.CheckCircleOutline
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.yalladrop.models.BottomNavigationBar
import com.example.yalladrop.models.OrderState

@Composable
fun Notifications(navController: NavHostController) {

    var listCard =  listOf<notificationItem>(notificationItem("Today 10:30PM" , OrderState.ACTIVE) ,notificationItem("Today 10:30PM" , OrderState.CANCELED),notificationItem("Today 10:30PM" , OrderState.COMPLETED) ,notificationItem("Today 10:30PM" , OrderState.ACTIVE), notificationItem("Today 10:30PM" , OrderState.COMPLETED))

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 100.dp, top = 40.dp)
                .padding(horizontal = 30.dp)
                .verticalScroll(rememberScrollState())
            ,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Default.ArrowBackIosNew,
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier.clickable {

                    }
                )

                Text(
                    text = "Notifications",
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.titleLarge
                )

            }

            Spacer(modifier = Modifier.padding(top = 25.dp))
            listCard.forEach { item -> NotificationCard(item.date , item.type) }


        }




        Box(modifier = Modifier.align(Alignment.BottomCenter),) {
            BottomNavigationBar(
                navController = navController
            )
        }
    }
}



@Composable
fun NotificationCard(date : String , type : OrderState){

    var iconType : ImageVector ;
    var msg : String ;
    if(type == OrderState.CANCELED){
        iconType = Icons.Default.Cancel;

        msg = "Order failed"
    }else if ( type == OrderState.ACTIVE){
        msg = "Orders are arriving soon"
        iconType = Icons.Default.Info
    }else{
        msg = "Order Successfully placed"
        iconType = Icons.Default.CheckCircleOutline
    }

    Row (
        modifier = Modifier
            .padding(top = 15.dp)
            .fillMaxWidth()
            .height(70.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(color = MaterialTheme.colorScheme.secondary)
            .padding(15.dp),

        ){
        Icon(
            iconType,
            contentDescription = null,
            tint = Color.Black,
            modifier = Modifier
                .size(25.dp)
                .clickable {

                }
        )
        Column(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(40.dp)
                .padding(start = 10.dp, end = 10.dp),
            horizontalAlignment = Alignment.Start ,
            verticalArrangement = Arrangement.SpaceBetween

            ) {
            Text(text = msg, style = MaterialTheme.typography.displayMedium , fontWeight = FontWeight.SemiBold , fontSize = 21.sp)
            Text(text = date, style = MaterialTheme.typography.labelSmall)
        }
        Icon(
            Icons.Default.Close,
            contentDescription = null,
            tint = Color.Black,
            modifier = Modifier
                .size(25.dp)
                .clickable {

                }
        )
    }
}

class notificationItem(
    var date : String ,
    var type : OrderState,
)