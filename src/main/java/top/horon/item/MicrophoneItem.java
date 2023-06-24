package top.horon.item;

import com.mojang.datafixers.types.templates.List;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

// 选中Item，Ctrl+H可以打开继承Item类的所有物品
public class MicrophoneItem extends Item {

    public MicrophoneItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        broadcast(user);
        user.getItemCooldownManager().set(this, 50);    // 广播间隔50tick=2.5s
        // user.playSound(SoundEvents.BLOCK_WOOL_BREAK, 1.0F, 1.0F);
        return super.use(world, user, hand);
        // return TypedActionResult.success(user.getStackInHand(hand));

    }

    private void broadcast(PlayerEntity user) {
        user.sendMessage(Text.literal("OK兄弟们，看我看我！我宣布个事，我是个傻*！"));
    }

}
