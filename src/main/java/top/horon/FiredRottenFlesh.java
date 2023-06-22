package top.horon;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

// 新增物品“烤制腐肉”右键使用以及声音效果
public class FiredRottenFlesh extends Item {

    public FiredRottenFlesh(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        playerEntity.playSound(SoundEvents.BLOCK_WOOL_BREAK, 1.0F, 1.0F);
        return TypedActionResult.success(playerEntity.getStackInHand(hand));
    }
    // 新增物品“烤制腐肉”
    public static FiredRottenFlesh Fired_Rotten_Flesh = new FiredRottenFlesh(new FabricItemSettings());
}