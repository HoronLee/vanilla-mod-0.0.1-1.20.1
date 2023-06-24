package top.horon.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.*;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.List;

public class FiredRottenFlesh extends Item {
    public static final FoodComponent Fired_Rotten_Flesh = new FoodComponent.Builder().hunger(5)
            .saturationModifier(0.8f).meat().build();

    public FiredRottenFlesh(Settings settings) {
        super(settings);
    }
    // 添加物品提示-未生效
    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {

        // 白色字体
        tooltip.add(Text.translatable("item.vanilla.fired_rotten_flesh.tag"));

        // 红色字体
        tooltip.add(Text.translatable("item.vanilla.fired_rotten_flesh.tag").formatted(Formatting.RED));
    }
}
