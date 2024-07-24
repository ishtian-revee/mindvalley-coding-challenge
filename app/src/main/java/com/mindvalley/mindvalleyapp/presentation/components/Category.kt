package com.mindvalley.mindvalleyapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode
import com.mindvalley.mindvalleyapp.common.Constants.MAX_CATEGORY_COLUMN_COUNT
import com.mindvalley.mindvalleyapp.domain.model.Category
import com.mindvalley.mindvalleyapp.presentation.theme.Grey
import com.mindvalley.mindvalleyapp.presentation.theme.Typography
import com.mindvalley.mindvalleyapp.presentation.theme.White
import com.mindvalley.mindvalleyapp.presentation.util.showShimmerEffect

@Composable
fun Category(categoryList: List<Category>) {
    FlowRow(
        modifier = Modifier
            .padding(top = 8.dp, bottom = 20.dp),
        mainAxisSize = SizeMode.Expand,
        mainAxisAlignment = FlowMainAxisAlignment.SpaceBetween
    ) {
        categoryList.forEach { category ->
            CategoryItem(category = category)
        }
    }
}

@Composable
fun CategoryItem(category: Category) {
    val itemSize: Dp = (LocalConfiguration.current.screenWidthDp.dp / MAX_CATEGORY_COLUMN_COUNT)
    Box(
        modifier = Modifier
            .size(itemSize, 80.dp)
            .padding(8.dp)
            .clip(RoundedCornerShape(50.dp))
            .background(Grey.copy(alpha = .2f)),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = category.name ?: "-",
            style = Typography.titleLarge,
            color = White
        )
    }
}

@Composable
fun CategoryShimmer(modifier: Modifier) {
    val itemSize: Dp = (LocalConfiguration.current.screenWidthDp.dp / MAX_CATEGORY_COLUMN_COUNT)
    Column(
        modifier = modifier
            .padding(top = 16.dp)
    ) {
        repeat(2) {
            Row(
                modifier = modifier
                    .padding(top = 8.dp)
            ) {
                repeat(2) {
                    Box(
                        modifier = Modifier
                            .size(itemSize, 80.dp)
                            .padding(8.dp)
                            .clip(RoundedCornerShape(50.dp))
                            .background(Grey.copy(alpha = .2f))
                            .showShimmerEffect()
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}