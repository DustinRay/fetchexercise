import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ItemList(items: List<Item>) {
    // Filter out items with blank or null names
    val filteredItems = items.filter { !it.name.isNullOrBlank() }

    // Sort items by listId first, then by the numeric part of the name
    val sortedItems = filteredItems.sortedWith(compareBy<Item> { it.listId }.thenBy { item ->
        item.name?.substringAfter("Item ")?.toIntOrNull() ?: Int.MAX_VALUE
    })

    // Group the filtered items
    val groupedItems = sortedItems.groupBy { it.listId }

    LazyColumn {
        groupedItems.forEach { (listId, itemList) ->
            item {
                Text(
                    text = "List $listId",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(8.dp),
                    fontWeight = FontWeight.Bold
                )
            }
            items(itemList) { item ->
                ItemCard(item)
            }
        }
    }
}

@Composable
fun ItemCard(item: Item) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .background(Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp).fillMaxWidth()) {
            Row {
                Text(
                    text = "Name: ",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(text = "${item.name}", style = MaterialTheme.typography.bodyLarge)
            }
            Row{
                Text(
                    text = "List ID: ",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(text = "${item.listId}", style = MaterialTheme.typography.bodyMedium)
            }
            Row{
                Text(
                    text = "ID: ",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(text = "${item.id}", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}
