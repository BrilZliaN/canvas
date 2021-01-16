@file:JvmName("BasicTest")

package com.mattworzala.canvas

import com.mattworzala.canvas.extra.col
import com.mattworzala.canvas.extra.row
import net.minestom.server.chat.ChatColor
import net.minestom.server.chat.ColoredText
import net.minestom.server.item.ItemStack
import net.minestom.server.item.Material
import kotlin.math.max
import kotlin.math.min

/* "Exported" Components */

fun RenderContext<*>.singleItem(index: Int, propHandler: ItemProps.() -> Unit = {}) =
    child(index, SingleItemFromProps, ItemProps(), propHandler)

fun RenderContext<*>.counter(index: Int) = child(index, BasicCounter, BlankProps, {})

@JvmField
val BasicItems = FunctionComponent<Props>(9, 5) {
    get(0).item {
        material = Material.GOLD_INGOT
    }
    get(10).item {
        material = Material.GOLD_INGOT
    }

    counter(3)

    singleItem(1) {
        item = ItemStack(Material.IRON_SHOVEL, 5)
    }

    singleItem(25) {
        item = ItemStack(Material.IRON_HELMET, 5)
    }

    row(4) {
        item = ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1)
    }
    val border = ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1)
    col(8) {
        item = border
    }
}

class ItemProps(var item: ItemStack = ItemStack(Material.GOLD_INGOT, 1)) : Props()
@JvmField
val SingleItemFromProps = FunctionComponent<ItemProps>(1, 1) {
    val slot = get(0)
    slot.item = props.item
    slot.onClick = {
        println("SingleItem was clicked!!!")
    }
}

@JvmField
val BasicCounter = FunctionComponent<Props>(3, 1) {
    var counter by useState(1)

    // Decrement
    slot(0) {
        onClick { counter = max(1, counter - 1) }
        item {
            material = Material.RED_CONCRETE
            displayName = ColoredText.of(ChatColor.RED, "Decrement")
        }
    }

    // Counter
    item(1) {
        material = Material.GLOWSTONE_DUST
        amount = counter.toByte()
    }

    // Increment
    slot(2) {
        onClick {
            counter = min(64, counter + 1)
        }
        item {
            material = Material.GREEN_CONCRETE
            displayName = ColoredText.of(ChatColor.BRIGHT_GREEN, "Increment")
        }
    }
}

