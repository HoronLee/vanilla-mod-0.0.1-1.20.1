package top.horon;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.impl.itemgroup.FabricItemGroupBuilderImpl;
import net.minecraft.block.Blocks;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.horon.item.MicrophoneItem;
import top.horon.item.FiredRottenFlesh;

public class VanillaMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("vanilla");
	// 新增物品“烤制腐肉”
	public static final Item Fired_Rotten_Flesh = new Item(new FabricItemSettings().food(FiredRottenFlesh.Fired_Rotten_Flesh));
	// 新增物品组
	private static final ItemGroup Vanilla_Mod = FabricItemGroup.builder()
			.icon(() -> new ItemStack(Fired_Rotten_Flesh))
			.displayName(Text.translatable("itemGroup.vanilla.vanilla"))
			.build();
	// 新增物品“麦克风”
	public static final Item MICROPHONE = new MicrophoneItem(new Item.Settings().maxCount(1));
	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Welcome to Vanilla Mod!");
		// 注册物品“烤制腐肉”
		Registry.register(Registries.ITEM, new Identifier("vanilla", "fired_rotten_flesh"), Fired_Rotten_Flesh);
		// “烤制腐肉”放在腐肉后面
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(content -> {
			content.addAfter(Items.ROTTEN_FLESH, Fired_Rotten_Flesh);
		});
		// 注册新增的物品组
		Registry.register(Registries.ITEM_GROUP, new Identifier("vanilla", "vanilla"), Vanilla_Mod);
		// 注册物品“麦克风”
		Registry.register(Registries.ITEM, new Identifier("vanilla", "microphone"), MICROPHONE);
		// “麦克风”放在诡谲钓竿后
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(content -> {
			content.addAfter(Items.WARPED_FUNGUS_ON_A_STICK, MICROPHONE);
		});
	}
}
/*
1.19.3版本开始官方对物品的注册和分组代码进行了更改，因此本视频的一些操作失效了。
更改的具体内容可以参照https://fabricmc.net/2022/11/24/1193.html
具体来说，ModFoodComponents.java文件中的代码无需改动，入口Class文件（即视频6分9秒开始编写的文件）中定义Item对象的代码改为：
	public static final Item 食物名 = new Item(new FabricItemSettings().food(ModFoodComponents.食物名));
此外，onInitialize函数内的注册操作改为：
	Registry.register(Registries.ITEM, new Identifier("小写模组ID", "小写食物名"), 食物名);
此时应能从游戏内的"/give @s 模组名:食物名"命令中获取到该物品
若要将该物品添加到原版已有的物品分组中，可同样在onInitialize函数内添加：
	ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(content -> {
		content.addAfter(Items.PUMPKIN_PIE, 食物名);
	});
将该物品添加到原版食物与饮品(FOOD_AND_DRINK)分组内的南瓜饼(PUMPKIN_PIE)后
遇到其他问题可以参考Fabric官方给出的中文文档https://fabricmc.net/wiki/zh_cn:start
 */