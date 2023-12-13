package com.example.lab1v2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.lab1v2.ui.theme.Lab1V2Theme
data class Person (val name:String,
                   val studentID:String,
                   val imageID:Int)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab1V2Theme {
                    PersonApp()
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonApp(
    navController: NavHostController = rememberNavController()
){
    val backStackEntry by navController.currentBackStackEntryAsState()
    var currentScreen = backStackEntry?.destination?.route ?: "List"
    if(currentScreen.contains("/"))
        currentScreen = currentScreen.split("/")[0]

    Scaffold(
        topBar ={
            MyAppBar(
                title= currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) {
            paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "List",
            modifier = Modifier.padding(paddingValues)
        ){
            composable(route = "List"){
                PersonsList(onItemClick = { userId ->
                    navController.navigate(route = "Detail/$userId")
                })
            }
            composable(route = "Detail/{userId}"){
                    backStackEntry -> PersonDetail(navController, userID = backStackEntry.arguments?.getString("userId"))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAppBar(
    title: String,
    modifier: Modifier = Modifier,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
){
    TopAppBar(
        title = { Text(title) },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back Navigation"
                    )
                }
            }
        }
    )
}


@Composable
fun PersonsList(onItemClick: (String) -> Unit,){
    var persons: List<Person> = listOf<Person>(
        Person("นายกมล จันบุตรดี", "643450063-8", R.drawable.ake),
        Person("นายจักรพรรดิ์ อนุไพร", " 643450065-4", R.drawable.dsc_7157),
        Person("นายเจษฎา ลีรัตน์", "643450067-0", R.drawable.dsc_7236),
        Person("นายชาญณรงค์ แต่งเมือง", " 643450069-6", R.drawable.dsc_7141),
        Person("นายณัฐกานต์ อินทรพานิชย์", " 643450072-7", R.drawable.ww),
        Person("นางสาวทัศนีย์ มลาตรี", "643450075-1", R.drawable.tuk),
        Person("นายธนาธิป เตชะ", "643450076-9", R.drawable.jj),
        Person("นายปรเมศวร์ สิทธิมงคล", "643450078-5", R.drawable.dsc_7177),
        Person("นายประสิทธิชัย จันทร์สม", "643450079-3", R.drawable.fa),
        Person("นางสาวพรธิตา ขานพล", "643450080-8", R.drawable.dsc_7119),
        Person("นายพีระเดช โพธิ์หล้า", "643450082-4", R.drawable.ppp),
        Person("นายวิญญู พรมภิภักดิ์", "643450084-0", R.drawable.dsc_7229),
        Person("นางสาวศรสวรรค์ ไพรอนันต์", "643450085-8", R.drawable.bo),
        Person("นายศรันย์ ซุ่นเส้ง", "643450086-6", R.drawable.dsc_7256),
        Person("นางสาวสุธาดา โสภาพ", "643450089-0", R.drawable.n),
        Person("นายอภิทุน ดวงจันทร์คล้อย", "643450092-1", R.drawable.dsc_7137),
        Person("นางสาวอมรรัตน์ มาระเหว", "643450094-7", R.drawable.dsc_7164),
        Person("นายอรัญ ศรีสวัสดิ์", "643450095-5", R.drawable.dsc_7108),
        Person("นางสาวกฤติยา สินโพธิ์", "643450320-4", R.drawable.ki),
        Person("นายก้องภพ ตาดี", "643450321-2", R.drawable.model),
        Person("นายเกรียงศักดิ์ หมู่เมืองสอง", "643450322-0", R.drawable.ll),
        Person("นายเจษฏา พบสมัย", "643450323-8", R.drawable.bank),
        Person("นายเทวารัณย์ ชัยกิจ", "643450324-6", R.drawable.run),
        Person("นายธนบดี สวัสดี", "643450325-4", R.drawable.dsc_7114),
        Person("นางสาวนภัสสร ดวงจันทร์", "643450326-2", R.drawable.cream),
        Person("นางสาวนภาปิลันธ์ ชาวชายโขง", "643450327-0", R.drawable.dsc_7206),
        Person("นางสาววรรณภา เบ้านาค", "643450330-1", R.drawable.dsc_7194),
        Person("นายศุภชัย แสนประสิทธิ์", "643450332-7", R.drawable.beaa),
        Person("นางสาวอฆพร ไร่ขาม", " 643450334-3", R.drawable.dsc_7217),
        Person("นายกฤตวัฒน์ อินทรสิทธิ์", "643450644-8", R.drawable.dsc_7274),
        Person("นางสาวณัฐธิดา บุญพา", "643450647-2", R.drawable.jan),
        Person(" นายรัตพงษ์ ปานเจริญ", "643450650-3", R.drawable.tey)
    )

    val context = LocalContext.current
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ){
        items(persons){
                persons -> PersonListItem(data = persons, onClick = onItemClick)
        }
    }
}
@Composable
fun PersonListItem(data:Person, onClick: (String) -> Unit){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(
                horizontal = 10.dp,
                vertical = 10.dp
            )
            .fillMaxWidth()
            .clickable { onClick(data.studentID) }){
        Image(
            painter = painterResource(id = data.imageID),
            contentDescription = "Avatar image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape))
        Spacer(modifier = Modifier.width(16.dp))
        Column  {
            Text(data.name)
            Text(data.studentID)
        }}
}
/*
@Composable
fun PersonDetail(navController: NavHostController = rememberNavController(),
                 userID: String?){
    var displayText: String = "Hello world"
    if(userID != null)
        displayText = userID
    Text(text = displayText)
}*/
@Composable
fun PersonDetail(navController: NavHostController = rememberNavController(), userID: String?) {
    var persons: List<Person> = listOf<Person>(
        Person("นายกมล จันบุตรดี", "643450063-8", R.drawable.ake),
        Person("นายจักรพรรดิ์ อนุไพร", " 643450065-4", R.drawable.dsc_7157),
        Person("นายเจษฎา ลีรัตน์", "643450067-0", R.drawable.dsc_7236),
        Person("นายชาญณรงค์ แต่งเมือง", " 643450069-6", R.drawable.dsc_7141),
        Person("นายณัฐกานต์ อินทรพานิชย์", " 643450072-7", R.drawable.ww),
        Person("นางสาวทัศนีย์ มลาตรี", "643450075-1", R.drawable.tuk),
        Person("นายธนาธิป เตชะ", "643450076-9", R.drawable.jj),
        Person("นายปรเมศวร์ สิทธิมงคล", "643450078-5", R.drawable.dsc_7177),
        Person("นายประสิทธิชัย จันทร์สม", "643450079-3", R.drawable.fa),
        Person("นางสาวพรธิตา ขานพล", "643450080-8", R.drawable.dsc_7119),
        Person("นายพีระเดช โพธิ์หล้า", "643450082-4", R.drawable.ppp),
        Person("นายวิญญู พรมภิภักดิ์", "643450084-0", R.drawable.dsc_7229),
        Person("นางสาวศรสวรรค์ ไพรอนันต์", "643450085-8", R.drawable.bo),
        Person("นายศรันย์ ซุ่นเส้ง", "643450086-6", R.drawable.dsc_7256),
        Person("นางสาวสุธาดา โสภาพ", "643450089-0", R.drawable.n),
        Person("นายอภิทุน ดวงจันทร์คล้อย", "643450092-1", R.drawable.dsc_7137),
        Person("นางสาวอมรรัตน์ มาระเหว", "643450094-7", R.drawable.dsc_7164),
        Person("นายอรัญ ศรีสวัสดิ์", "643450095-5", R.drawable.dsc_7108),
        Person("นางสาวกฤติยา สินโพธิ์", "643450320-4", R.drawable.ki),
        Person("นายก้องภพ ตาดี", "643450321-2", R.drawable.model),
        Person("นายเกรียงศักดิ์ หมู่เมืองสอง", "643450322-0", R.drawable.ll),
        Person("นายเจษฏา พบสมัย", "643450323-8", R.drawable.bank),
        Person("นายเทวารัณย์ ชัยกิจ", "643450324-6", R.drawable.run),
        Person("นายธนบดี สวัสดี", "643450325-4", R.drawable.dsc_7114),
        Person("นางสาวนภัสสร ดวงจันทร์", "643450326-2", R.drawable.cream),
        Person("นางสาวนภาปิลันธ์ ชาวชายโขง", "643450327-0", R.drawable.dsc_7206),
        Person("นางสาววรรณภา เบ้านาค", "643450330-1", R.drawable.dsc_7194),
        Person("นายศุภชัย แสนประสิทธิ์", "643450332-7", R.drawable.beaa),
        Person("นางสาวอฆพร ไร่ขาม", " 643450334-3", R.drawable.dsc_7217),
        Person("นายกฤตวัฒน์ อินทรสิทธิ์", "643450644-8", R.drawable.dsc_7274),
        Person("นางสาวณัฐธิดา บุญพา", "643450647-2", R.drawable.jan),
        Person(" นายรัตพงษ์ ปานเจริญ", "643450650-3", R.drawable.tey)
    )
    val selectedPerson = persons.find { it.studentID == userID }

    if (selectedPerson != null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "${selectedPerson.name}",
                style = MaterialTheme.typography.headlineLarge
            )
            Spacer(modifier = Modifier.height(16.dp))
            Image(
                painter = painterResource(id = selectedPerson.imageID),
                contentDescription = "Avatar image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Student ID: ${selectedPerson.studentID}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Branch: CIS")

            Button(
                onClick = { navController.navigateUp() },
                modifier = Modifier.align(Alignment.End)
            ) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                Text(text = "Back to List")
            }
        }
    } else {
        Text(text = "Person not found")
    }
}
