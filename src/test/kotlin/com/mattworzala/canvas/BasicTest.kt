@file:JvmName("BasicTest")

package com.mattworzala.canvas

import com.mattworzala.canvas.extra.col
import com.mattworzala.canvas.extra.row
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.minestom.server.item.ItemStack
import net.minestom.server.item.Material
import kotlin.math.max
import kotlin.math.min

@JvmField
val BasicItems = fragment(9, 5) {
    this[0].item {
        material = Material.GOLD_INGOT
    }

    this[10].item {
        material = Material.GOLD_INGOT
    }

    BasicCounter(this, 3)

    SingleItemFromProps(this, 1) {
        this["item"] = ItemStack(Material.IRON_SHOVEL, 5)
    }

    SingleItemFromProps(this, 25) {
        this["item"] = ItemStack(Material.IRON_HELMET, 5)
    }

    row(4) {
        item = ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1)
    }
    val border = ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1)
    col(8) {
        item = border
    }
}

@JvmField
val SingleItemFromProps = fragment(1, 1) {
    this[0].apply {
        item = data["item"]!!
        onClick {
            println("SingleItem was clicked!!!")
        }
    }
}

@JvmField
val BasicCounter = fragment(3, 1) {
    var counter by useState(1)

    // Decrement
    this[0].apply {
        onClick { counter = max(1, counter - 1) }
        item {
            material = Material.RED_CONCRETE
            displayName = Component.text("Decrement", NamedTextColor.RED)
        }
    }

    // Counter
    this[1].item {
        material = Material.GLOWSTONE_DUST
        amount = counter.toByte()
    }

    // Increment
    this[0].apply {
        onClick {
            counter = min(64, counter + 1)
        }
        item {
            material = Material.GREEN_CONCRETE
            displayName = Component.text("Increment", NamedTextColor.GREEN)
        }
    }
}

