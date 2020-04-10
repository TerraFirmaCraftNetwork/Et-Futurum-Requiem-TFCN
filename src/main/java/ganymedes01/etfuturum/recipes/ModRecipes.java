package ganymedes01.etfuturum.recipes;

import ganymedes01.etfuturum.EtFuturum;
import ganymedes01.etfuturum.ModBlocks;
import ganymedes01.etfuturum.ModItems;
import ganymedes01.etfuturum.blocks.ConcreteRegistry;
import ganymedes01.etfuturum.blocks.Stone;
import ganymedes01.etfuturum.configuration.ConfigurationHandler;
import ganymedes01.etfuturum.core.utils.EnumDyeColor;
import ganymedes01.etfuturum.lib.EnumColour;
import ganymedes01.etfuturum.lib.Reference;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.RecipeSorter;
import net.minecraftforge.oredict.RecipeSorter.Category;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModRecipes {

    public static String[] dyes = new String[] { "dyeBlack", "dyeRed", "dyeGreen", "dyeBrown", "dyeBlue", "dyePurple", "dyeCyan", "dyeLightGray", "dyeGray", "dyePink", "dyeLime", "dyeYellow", "dyeLightBlue", "dyeMagenta", "dyeOrange", "dyeWhite" };
    private static final ItemStack _SAND_ = new ItemStack(Blocks.sand), _GRAVEL_ = new ItemStack(Blocks.gravel);
    
    
    public static void init() {
        if (ConfigurationHandler.enableBanners) {
            RecipeSorter.register(Reference.MOD_ID + ".RecipeDuplicatePattern", RecipeDuplicatePattern.class, Category.SHAPELESS, "after:minecraft:shapeless");
            RecipeSorter.register(Reference.MOD_ID + ".RecipeAddPattern", RecipeAddPattern.class, Category.SHAPED, "after:minecraft:shaped");
        }

        registerOreDictionary();
        registerRecipes();
        tweakRecipes();
    }

    private static void tweakRecipes() {
        if (ConfigurationHandler.enableDoors) {
            Items.wooden_door.setMaxStackSize(64);
            Items.iron_door.setMaxStackSize(64);
            removeFirstRecipeFor(Items.wooden_door);
            removeFirstRecipeFor(Items.iron_door);
            removeFirstRecipeFor(Blocks.trapdoor);
        }

        if (ConfigurationHandler.enableFences) {
            removeFirstRecipeFor(Blocks.fence);
            removeFirstRecipeFor(Blocks.fence_gate);
            Blocks.fence.setCreativeTab(null);
            Blocks.fence_gate.setCreativeTab(null);
        }

        if (ConfigurationHandler.enableBurnableBlocks) {
            Blocks.fire.setFireInfo(Blocks.fence_gate, 5, 20);
            Blocks.fire.setFireInfo(Blocks.fence, 5, 20);
            Blocks.fire.setFireInfo(Blocks.deadbush, 60, 100);
        }
        
        if (ConfigurationHandler.enableWoodRedstone) {
            removeFirstRecipeFor(Blocks.wooden_button);
            removeFirstRecipeFor(Blocks.wooden_pressure_plate);
        }
    }

    private static void registerOreDictionary() {
        OreDictionary.registerOre("chestWood", new ItemStack(Blocks.chest));
        OreDictionary.registerOre("bookshelfWood", new ItemStack(Blocks.bookshelf));
        OreDictionary.registerOre("doorWood", new ItemStack(Blocks.wooden_door));
        OreDictionary.registerOre("trapdoorWood", Blocks.trapdoor);
        OreDictionary.registerOre("fenceWood", new ItemStack(Blocks.fence));
        OreDictionary.registerOre("fenceGateWood", new ItemStack(Blocks.fence_gate));
        OreDictionary.registerOre("buttonWood", new ItemStack(Blocks.wooden_button));
        OreDictionary.registerOre("pressurePlateWood", new ItemStack(Blocks.wooden_pressure_plate));
        OreDictionary.registerOre("doorIron", new ItemStack(Items.iron_door));
        OreDictionary.registerOre("buttonStone", new ItemStack(Blocks.stone_button));
        OreDictionary.registerOre("pressurePlateStone", new ItemStack(Blocks.stone_pressure_plate));
        OreDictionary.registerOre("pressurePlateIron", new ItemStack(Blocks.heavy_weighted_pressure_plate));
        OreDictionary.registerOre("pressurePlateGold", new ItemStack(Blocks.light_weighted_pressure_plate));

        if (ConfigurationHandler.enablePrismarine) {
            OreDictionary.registerOre("shardPrismarine", new ItemStack(ModItems.prismarine_shard));
            OreDictionary.registerOre("crystalPrismarine", new ItemStack(ModItems.prismarine_crystals));
            OreDictionary.registerOre("blockPrismarine", new ItemStack(ModBlocks.prismarine, 1, OreDictionary.WILDCARD_VALUE));
        }

        if (ConfigurationHandler.enableDoors) {
            for (int i=0;i<5;i++)
                OreDictionary.registerOre("doorWood", new ItemStack(ModBlocks.doors[i]));
            for (int i=0;i<5;i++)
                OreDictionary.registerOre("trapdoorWood", ModBlocks.trapdoors[i]);
        }

        if (ConfigurationHandler.enableFences) {
            for (int i = 0; i < ModBlocks.fences.length; i++)
                OreDictionary.registerOre("fenceWood", new ItemStack(ModBlocks.fences[i]));
            for (int i = 0; i < ModBlocks.gates.length; i++)
                OreDictionary.registerOre("fenceGateWood", new ItemStack(ModBlocks.gates[i]));
        }

        if (ConfigurationHandler.enableWoodRedstone) {
            for (int i = 0; i < ModBlocks.buttons.length; i++)
                OreDictionary.registerOre("buttonWood", new ItemStack(ModBlocks.buttons[i]));
            for (int i = 0; i < ModBlocks.pressurePlates.length; i++)
                OreDictionary.registerOre("pressurePlateWood", new ItemStack(ModBlocks.pressurePlates[i]));
        }

        if (ConfigurationHandler.enableStones) {
            OreDictionary.registerOre("stoneGranite", new ItemStack(ModBlocks.stone, 1, Stone.GRANITE));
            OreDictionary.registerOre("stoneDiorite", new ItemStack(ModBlocks.stone, 1, Stone.DIORITE));
            OreDictionary.registerOre("stoneAndesite", new ItemStack(ModBlocks.stone, 1, Stone.ANDESITE));
            OreDictionary.registerOre("stoneGranitePolished", new ItemStack(ModBlocks.stone, 1, Stone.POLISHED_GRANITE));
            OreDictionary.registerOre("stoneDioritePolished", new ItemStack(ModBlocks.stone, 1, Stone.POLISHED_DIORITE));
            OreDictionary.registerOre("stoneAndesitePolished", new ItemStack(ModBlocks.stone, 1, Stone.POLISHED_ANDESITE));
        }

        if (ConfigurationHandler.enableSlimeBlock)
            OreDictionary.registerOre("blockSlime", new ItemStack(ModBlocks.slime));

        if (ConfigurationHandler.enableIronTrapdoor)
            OreDictionary.registerOre("trapdoorIron", ModBlocks.iron_trapdoor);

        if (ConfigurationHandler.enableBeetroot)
            OreDictionary.registerOre("cropBeetroot", ModItems.beetroot);

        if (ConfigurationHandler.enableChorusFruit)
            OreDictionary.registerOre("brickEndStone", ModBlocks.end_bricks);
    }

    private static void registerRecipes() {
        if (ConfigurationHandler.enableStoneBrickRecipes) {
            addShapelessRecipe(new ItemStack(Blocks.mossy_cobblestone), new ItemStack(Blocks.cobblestone), new ItemStack(Blocks.vine));
            addShapelessRecipe(new ItemStack(Blocks.stonebrick, 1, 1), new ItemStack(Blocks.stonebrick), new ItemStack(Blocks.vine));
            addShapedRecipe(new ItemStack(Blocks.stonebrick, 1, 3), "x", "x", 'x', new ItemStack(Blocks.stone_slab, 1, 5));
            GameRegistry.addSmelting(new ItemStack(Blocks.stonebrick), new ItemStack(Blocks.stonebrick, 1, 2), 0.0F);
        }

        if (ConfigurationHandler.enableSlimeBlock) {
            addShapedRecipe(new ItemStack(ModBlocks.slime), "xxx", "xxx", "xxx", 'x', "slimeball");
            addShapelessRecipe(new ItemStack(Items.slime_ball, 9), ModBlocks.slime);
        }

        if (ConfigurationHandler.enableCoarseDirt)
            addShapedRecipe(new ItemStack(ModBlocks.coarse_dirt, 4), "xy", "yx", 'x', new ItemStack(Blocks.dirt), 'y', new ItemStack(Blocks.gravel));

        if (ConfigurationHandler.enableMutton)
            GameRegistry.addSmelting(ModItems.raw_mutton, new ItemStack(ModItems.cooked_mutton), 0.35F);

        if (ConfigurationHandler.enableIronTrapdoor)
            addShapedRecipe(new ItemStack(ModBlocks.iron_trapdoor), "xx", "xx", 'x', "ingotIron");

        if (ConfigurationHandler.enableStones) {
            // Diorite
            addShapedRecipe(new ItemStack(ModBlocks.stone, 2, Stone.DIORITE), "xy", "yx", 'x', new ItemStack(Blocks.cobblestone), 'y', "gemQuartz");
            addShapedRecipe(new ItemStack(ModBlocks.stone, 4, Stone.POLISHED_DIORITE), "xx", "xx", 'x', "stoneDiorite");
            // Andesite
            addShapelessRecipe(new ItemStack(ModBlocks.stone, 2, Stone.ANDESITE), new ItemStack(Blocks.cobblestone), "stoneDiorite");
            addShapedRecipe(new ItemStack(ModBlocks.stone, 4, Stone.POLISHED_ANDESITE), "xx", "xx", 'x', "stoneAndesite");
            // Granite
            addShapelessRecipe(new ItemStack(ModBlocks.stone, 2, Stone.GRANITE), "gemQuartz", "stoneDiorite");
            addShapedRecipe(new ItemStack(ModBlocks.stone, 4, Stone.POLISHED_GRANITE), "xx", "xx", 'x', "stoneGranite");
        }

        if (ConfigurationHandler.enablePrismarine) {
            int PLAIN = 0;
            int BRICKS = 1;
            int DARK = 2;

            addShapedRecipe(new ItemStack(ModBlocks.prismarine, 1, DARK), "xxx", "xyx", "xxx", 'x', "shardPrismarine", 'y', "dyeBlack");
            addShapedRecipe(new ItemStack(ModBlocks.prismarine, 1, PLAIN), "xx", "xx", 'x', "shardPrismarine");
            addShapedRecipe(new ItemStack(ModBlocks.prismarine, 1, BRICKS), "xxx", "xxx", "xxx", 'x', "shardPrismarine");
            addShapedRecipe(new ItemStack(ModBlocks.sea_lantern), "xyx", "yyy", "xyx", 'x', "shardPrismarine", 'y', "crystalPrismarine");

            addShapedRecipe(new ItemStack(ModBlocks.prismarineStairsRough, 4), "x  ", "xx ", "xxx", 'x', new ItemStack(ModBlocks.prismarine, 1, PLAIN));
            addShapedRecipe(new ItemStack(ModBlocks.prismarineStairsBrick, 4), "x  ", "xx ", "xxx", 'x', new ItemStack(ModBlocks.prismarine, 1, BRICKS));
            addShapedRecipe(new ItemStack(ModBlocks.prismarineStairsDark, 4), "x  ", "xx ", "xxx", 'x', new ItemStack(ModBlocks.prismarine, 1, DARK));
            
            addShapedRecipe(new ItemStack(ModBlocks.prismarineSlabRough, 6), "xxx", 'x', new ItemStack(ModBlocks.prismarine, 1, PLAIN));
            addShapedRecipe(new ItemStack(ModBlocks.prismarineSlabBrick, 6), "xxx", 'x', new ItemStack(ModBlocks.prismarine, 1, BRICKS));
            addShapedRecipe(new ItemStack(ModBlocks.prismarineSlabDark, 6), "xxx", 'x', new ItemStack(ModBlocks.prismarine, 1, DARK));
            
            if (ConfigurationHandler.enableRecipeForPrismarine && !Loader.isModLoaded("Botania")) {
                addShapedRecipe(new ItemStack(ModItems.prismarine_shard, 4), "xy", "zx", 'x', "gemQuartz", 'y', "dyeBlue", 'z', "dyeGreen");
                addShapedRecipe(new ItemStack(ModItems.prismarine_crystals, 4), "xy", "yx", 'x', "gemQuartz", 'y', "dustGlowstone");
            }
        }

        if (ConfigurationHandler.enableDoors) {
            for (int i = 0; i < ModBlocks.doors.length; i++)
                addShapedRecipe(new ItemStack(ModBlocks.doors[i], 3), "xx", "xx", "xx", 'x', new ItemStack(Blocks.planks, 1, i + 1));
            addShapedRecipe(new ItemStack(Items.wooden_door, 3), "xx", "xx", "xx", 'x', "plankWood");
            addShapedRecipe(new ItemStack(Items.iron_door, 3), "xx", "xx", "xx", 'x', "ingotIron");
            
            for (int i = 0; i < ModBlocks.trapdoors.length; i++)
                addShapedRecipe(new ItemStack(ModBlocks.trapdoors[i], 2), "xxx", "xxx", 'x', new ItemStack(Blocks.planks, 1, i + 1));
            addShapedRecipe(new ItemStack(Blocks.trapdoor, 2), "xxx", "xxx", 'x', "plankWood");
        }

        if (ConfigurationHandler.enableRedSandstone) {
            addShapedRecipe(new ItemStack(ModBlocks.red_sandstone), "xx", "xx", 'x', new ItemStack(Blocks.sand, 1, 1));
            addShapedRecipe(new ItemStack(ModBlocks.red_sandstone, 1, 1), "x", "x", 'x', new ItemStack(ModBlocks.red_sandstone_slab));
            addShapedRecipe(new ItemStack(ModBlocks.red_sandstone, 4, 2), "xx", "xx", 'x', new ItemStack(ModBlocks.red_sandstone));
            addShapedRecipe(new ItemStack(ModBlocks.red_sandstone_slab, 6), "xxx", 'x', ModBlocks.red_sandstone);
            addShapedRecipe(new ItemStack(ModBlocks.red_sandstone_stairs, 4), "x  ", "xx ", "xxx", 'x', ModBlocks.red_sandstone);
        }

        if (ConfigurationHandler.enableFences) {
            for (int i = 0; i < ModBlocks.fences.length; i++)
                addShapedRecipe(new ItemStack(ModBlocks.fences[i], 3), "xyx", "xyx", 'x', new ItemStack(Blocks.planks, 1, i), 'y', "stickWood");
            addShapedRecipe(new ItemStack(ModBlocks.fences[0], 3), "xyx", "xyx", 'x', "plankWood", 'y', "stickWood");
            addShapelessRecipe(new ItemStack(Blocks.fence), ModBlocks.fences[0]);
            addShapelessRecipe(new ItemStack(ModBlocks.fences[0]), Blocks.fence);

            for (int i = 0; i < ModBlocks.gates.length; i++)
                addShapedRecipe(new ItemStack(ModBlocks.gates[i]), "yxy", "yxy", 'x', new ItemStack(Blocks.planks, 1, i + 1), 'y', "stickWood");
            addShapedRecipe(new ItemStack(Blocks.fence_gate), "yxy", "yxy", 'x', "plankWood", 'y', "stickWood");
        }

        if (ConfigurationHandler.enableBanners) {
            for (EnumColour colour : EnumColour.values())
                addShapedRecipe(new ItemStack(ModBlocks.banner, 1, colour.getDamage()), new Object[] { "xxx", "xxx", " y ", 'x', new ItemStack(Blocks.wool, 1, colour.getDamage()), 'y', "stickWood" });
            GameRegistry.addRecipe(new RecipeDuplicatePattern());
            GameRegistry.addRecipe(new RecipeAddPattern());
        }

        if (ConfigurationHandler.enableArmourStand)
            addShapedRecipe(new ItemStack(ModItems.armour_stand), "xxx", " x ", "xyx", 'x', "stickWood", 'y', new ItemStack(Blocks.stone_slab));

        if (ConfigurationHandler.enableRabbit) {
            addShapedRecipe(new ItemStack(ModItems.rabbit_stew), " R ", "CPM", " B ", 'R', new ItemStack(ModItems.cooked_rabbit), 'C', Items.carrot, 'P', Items.baked_potato, 'M', Blocks.brown_mushroom, 'B', Items.bowl);
            addShapedRecipe(new ItemStack(ModItems.rabbit_stew), " R ", "CPD", " B ", 'R', new ItemStack(ModItems.cooked_rabbit), 'C', Items.carrot, 'P', Items.baked_potato, 'D', Blocks.red_mushroom, 'B', Items.bowl);
            GameRegistry.addSmelting(ModItems.raw_rabbit, new ItemStack(ModItems.cooked_rabbit), 0.35F);
            addShapedRecipe(new ItemStack(Items.leather), "xx", "xx", 'x', ModItems.rabbit_hide);
        }

        if (ConfigurationHandler.enableOldGravel)
            addShapedRecipe(new ItemStack(ModBlocks.old_gravel, 4), "xy", "yx", 'x', ModBlocks.coarse_dirt, 'y', Blocks.gravel);

        if (ConfigurationHandler.enableSponge) {
            addShapelessRecipe(new ItemStack(ModBlocks.sponge), Blocks.sponge);
            addShapelessRecipe(new ItemStack(Blocks.sponge), ModBlocks.sponge);
            GameRegistry.addSmelting(new ItemStack(ModBlocks.sponge, 1, 1), new ItemStack(ModBlocks.sponge), 0.0F);
        }

        if (ConfigurationHandler.enableBeetroot) {
            addShapedRecipe(new ItemStack(ModItems.beetroot_soup), "xxx", "xxx", " y ", 'x', "cropBeetroot", 'y', Items.bowl);
            addShapelessRecipe(new ItemStack(Items.dye, 1, 1), "cropBeetroot");
        }

        if (ConfigurationHandler.enableChorusFruit) {
            addShapedRecipe(new ItemStack(ModBlocks.purpur_block, 4), "xx", "xx", 'x', ModItems.popped_chorus_fruit);
            addShapedRecipe(new ItemStack(ModBlocks.purpur_stairs, 4), "x  ", "xx ", "xxx", 'x', ModBlocks.purpur_block);
            addShapedRecipe(new ItemStack(ModBlocks.purpur_slab, 6), "xxx", 'x', ModBlocks.purpur_block);
            addShapedRecipe(new ItemStack(ModBlocks.purpur_pillar), "x", "x", 'x', ModBlocks.purpur_slab);
            addShapedRecipe(new ItemStack(ModBlocks.end_bricks), "xx", "xx", 'x', Blocks.end_stone);
            GameRegistry.addSmelting(new ItemStack(ModItems.chorus_fruit), new ItemStack(ModItems.popped_chorus_fruit), 0.0F);
            addShapedRecipe(new ItemStack(ModBlocks.end_rod), "x", "y", 'x', Items.blaze_rod, 'y', ModItems.popped_chorus_fruit);
        }

        if (ConfigurationHandler.enableCryingObsidian)
            addShapelessRecipe(new ItemStack(ModBlocks.crying_obsidian), Blocks.obsidian, "gemLapis");

        if (ConfigurationHandler.enableLingeringPotions)
            addShapelessRecipe(new ItemStack(ModItems.dragon_breath), new ItemStack(Items.potionitem, 1, 8195), ModItems.chorus_fruit, ModItems.chorus_fruit);

        if (ConfigurationHandler.enableDragonRespawn)
            addShapedRecipe(new ItemStack(ModItems.end_crystal), "xxx", "xyx", "xzx", 'x', "blockGlassColorless", 'y', Items.ender_eye, 'z', Items.ghast_tear);

        if (ConfigurationHandler.enableRoses) {
            addShapelessRecipe(new ItemStack(Items.dye, 1, 1), ModBlocks.rose);
            addShapedRecipe(new ItemStack(Blocks.double_plant, 1, 4), "xx", "xx", "xx", 'x', new ItemStack(ModBlocks.rose));
            addShapedRecipe(new ItemStack(ModBlocks.rose, 12), "xx", 'x', new ItemStack(Blocks.double_plant, 1, 4));
        }

        if (ConfigurationHandler.enableTippedArrows && ConfigurationHandler.enableLingeringPotions) {
            RecipeSorter.register(Reference.MOD_ID + ".RecipeTippedArrow", RecipeTippedArrow.class, Category.SHAPED, "after:minecraft:shaped");
            GameRegistry.addRecipe(new RecipeTippedArrow(new ItemStack(ModItems.tipped_arrow), "xxx", "xyx", "xxx", 'x', Items.arrow, 'y', new ItemStack(ModItems.lingering_potion, 1, OreDictionary.WILDCARD_VALUE)));
        }


        if(!ConfigurationHandler.enableTileReplacement) { // Add recipes for updated tiles if tile replacement is disabled.
            if (ConfigurationHandler.enableColourfulBeacons) {
                removeFirstRecipeFor(Blocks.beacon); // Remove recipe for Minecrafts Beacon
                addShapedRecipe(new ItemStack(ModBlocks.beacon), "GGG", "GSG", "OOO", 'G', Blocks.glass, 'S', Items.nether_star, 'O', Blocks.obsidian);
                addShapelessRecipe(new ItemStack(ModBlocks.beacon), Blocks.beacon); // Minecraft Beacon -> EtFuturum Beacon
                addShapelessRecipe(new ItemStack(Blocks.beacon), ModBlocks.beacon); // EtFuturm Beacon -> Minecraft Beacon
            }
    
            if (ConfigurationHandler.enableEnchants) {
                removeFirstRecipeFor(Blocks.enchanting_table); // Remove recipe for Minecrafts Enchanting Table
                addShapedRecipe(new ItemStack(ModBlocks.enchantment_table), " B ", "D#D", "###", '#', Blocks.obsidian, 'B', Items.book, 'D', "genDiamond");
                addShapelessRecipe(new ItemStack(ModBlocks.enchantment_table), Blocks.enchanting_table); // Minecraft Enchanting Table -> EtFuturum Enchanting Table (For any old leftovers)
                addShapelessRecipe(new ItemStack(Blocks.enchanting_table), ModBlocks.enchantment_table); // EtFuturum Enchanting Table -> Minecraft Enchanting Table (For when you need to to craft something that has it as a component (ChickenChunks))
            }
    
            if (ConfigurationHandler.enableInvertedDaylightSensor) {
                removeFirstRecipeFor(Blocks.daylight_detector); // Remove recipe for Minecrafts Daylight Sensor
                addShapedRecipe(new ItemStack(ModBlocks.daylight_sensor), "GGG", "QQQ", "WWW", 'G', Blocks.glass, 'Q', "gemQuartz", 'W', "slabWood");
                addShapelessRecipe(new ItemStack(ModBlocks.daylight_sensor), Blocks.daylight_detector);
                addShapelessRecipe(new ItemStack(Blocks.daylight_detector), ModBlocks.daylight_sensor);
            }
            
            if (ConfigurationHandler.enableBrewingStands) {
                removeFirstRecipeFor(Items.brewing_stand); //gany pls, Blocks.brewing_stand is invalid
                addShapedRecipe(new ItemStack(ModBlocks.brewing_stand), " i ", "xxx", 'i', Items.blaze_rod, 'x', "cobblestone");
                addShapelessRecipe(new ItemStack(ModBlocks.brewing_stand), Items.brewing_stand); //vanilla brew > EtF brew
            }
        }
        
        if (ConfigurationHandler.enableNetherBlocks) {
            addShapedRecipe(new ItemStack(ModBlocks.magma), "xx", "xx", 'x', new ItemStack(Items.magma_cream) );
            addShapedRecipe(new ItemStack(ModBlocks.redNB), "wb", "bw", 'b', Items.netherbrick, 'w', Items.nether_wart  );
            
            addShapedRecipe(new ItemStack(ModBlocks.netherwart), "xxx", "xxx", "xxx", 'x', Items.nether_wart);
            addShapelessRecipe(new ItemStack(Items.nether_wart, 9), ModBlocks.netherwart);
            
        }
        if (ConfigurationHandler.enableBoneBlock) {
            addShapedRecipe(new ItemStack(ModBlocks.bone), "xxx", "xxx", "xxx", 'x', new ItemStack(Items.dye,1,15) );
        }
        
        if (ConfigurationHandler.enableConcrete) {
            for(EnumDyeColor dye : EnumDyeColor.values()){
                GameRegistry.addShapelessRecipe(new ItemStack(ConcreteRegistry.getPowderFromDye(dye), 8), _SAND_, _SAND_, _SAND_, _SAND_, _GRAVEL_, _GRAVEL_, _GRAVEL_, _GRAVEL_, new ItemStack(Items.dye, 1, dye.getDyeDamage()));
            }
        }
        
        if (ConfigurationHandler.enableRecipeForTotem) {
            addShapedRecipe(new ItemStack(ModItems.totem), "EBE", "GBG", " G ", 'E', "gemEmerald", 'G', "ingotGold", 'B', "blockGold" );
        }
        
        if (ConfigurationHandler.enableWoodRedstone) {
            for (int i = 0; i < ModBlocks.buttons.length; i++)
                addShapedRecipe(new ItemStack(ModBlocks.buttons[i], 1), "x", 'x', new ItemStack(Blocks.planks, 1, i + 1));
            addShapedRecipe(new ItemStack(Blocks.wooden_button, 1), "x", 'x', "plankWood");
            
            for (int i = 0; i < ModBlocks.pressurePlates.length; i++)
                addShapedRecipe(new ItemStack(ModBlocks.pressurePlates[i], 1), "xx", 'x', new ItemStack(Blocks.planks, 1, i + 1));
            addShapedRecipe(new ItemStack(Blocks.wooden_pressure_plate, 1), "xx", 'x', "plankWood");
        }
        
        if (ConfigurationHandler.enableStrippedLogs) {
            addShapedRecipe(new ItemStack(Blocks.planks, 4, 0), "x", 'x', new ItemStack(ModBlocks.log_stripped, 1, 0));
            addShapedRecipe(new ItemStack(Blocks.planks, 4, 1), "x", 'x', new ItemStack(ModBlocks.log_stripped, 1, 1));
            addShapedRecipe(new ItemStack(Blocks.planks, 4, 2), "x", 'x', new ItemStack(ModBlocks.log_stripped, 1, 2));
            addShapedRecipe(new ItemStack(Blocks.planks, 4, 3), "x", 'x', new ItemStack(ModBlocks.log_stripped, 1, 3));
            addShapedRecipe(new ItemStack(Blocks.planks, 4, 4), "x", 'x', new ItemStack(ModBlocks.log2_stripped, 1, 0));
            addShapedRecipe(new ItemStack(Blocks.planks, 4, 5), "x", 'x', new ItemStack(ModBlocks.log2_stripped, 1, 1));
        }
        
        if (ConfigurationHandler.enableBarkLogs) {
            addShapedRecipe(new ItemStack(ModBlocks.log_bark, 3, 0), "xx", "xx", 'x', new ItemStack(Blocks.log,1,0) );
            addShapedRecipe(new ItemStack(ModBlocks.log_bark, 3, 1), "xx", "xx", 'x', new ItemStack(Blocks.log,1,1) );
            addShapedRecipe(new ItemStack(ModBlocks.log_bark, 3, 2), "xx", "xx", 'x', new ItemStack(Blocks.log,1,2) );
            addShapedRecipe(new ItemStack(ModBlocks.log_bark, 3, 3), "xx", "xx", 'x', new ItemStack(Blocks.log,1,3) );
            addShapedRecipe(new ItemStack(ModBlocks.log2_bark, 3, 0), "xx", "xx", 'x', new ItemStack(Blocks.log2,1,0) );
            addShapedRecipe(new ItemStack(ModBlocks.log2_bark, 3, 1), "xx", "xx", 'x', new ItemStack(Blocks.log2,1,1) );
            
            addShapedRecipe(new ItemStack(Blocks.planks, 4, 0), "x", 'x', new ItemStack(ModBlocks.log_bark, 1, 0));
            addShapedRecipe(new ItemStack(Blocks.planks, 4, 1), "x", 'x', new ItemStack(ModBlocks.log_bark, 1, 1));
            addShapedRecipe(new ItemStack(Blocks.planks, 4, 2), "x", 'x', new ItemStack(ModBlocks.log_bark, 1, 2));
            addShapedRecipe(new ItemStack(Blocks.planks, 4, 3), "x", 'x', new ItemStack(ModBlocks.log_bark, 1, 3));
            addShapedRecipe(new ItemStack(Blocks.planks, 4, 4), "x", 'x', new ItemStack(ModBlocks.log2_bark, 1, 0));
            addShapedRecipe(new ItemStack(Blocks.planks, 4, 5), "x", 'x', new ItemStack(ModBlocks.log2_bark, 1, 1));
            if (ConfigurationHandler.enableStrippedLogs) {
                addShapedRecipe(new ItemStack(ModBlocks.wood_stripped, 3, 0), "xx", "xx", 'x', new ItemStack(ModBlocks.log_stripped,1,0) );
                addShapedRecipe(new ItemStack(ModBlocks.wood_stripped, 3, 1), "xx", "xx", 'x', new ItemStack(ModBlocks.log_stripped,1,1) );
                addShapedRecipe(new ItemStack(ModBlocks.wood_stripped, 3, 2), "xx", "xx", 'x', new ItemStack(ModBlocks.log_stripped,1,2) );
                addShapedRecipe(new ItemStack(ModBlocks.wood_stripped, 3, 3), "xx", "xx", 'x', new ItemStack(ModBlocks.log_stripped,1,3) );
                addShapedRecipe(new ItemStack(ModBlocks.wood2_stripped, 3, 0), "xx", "xx", 'x', new ItemStack(ModBlocks.log2_stripped,1,0) );
                addShapedRecipe(new ItemStack(ModBlocks.wood2_stripped, 3, 1), "xx", "xx", 'x', new ItemStack(ModBlocks.log2_stripped,1,1) );
                
                addShapedRecipe(new ItemStack(Blocks.planks, 4, 0), "x", 'x', new ItemStack(ModBlocks.wood_stripped, 1, 0));
                addShapedRecipe(new ItemStack(Blocks.planks, 4, 1), "x", 'x', new ItemStack(ModBlocks.wood_stripped, 1, 1));
                addShapedRecipe(new ItemStack(Blocks.planks, 4, 2), "x", 'x', new ItemStack(ModBlocks.wood_stripped, 1, 2));
                addShapedRecipe(new ItemStack(Blocks.planks, 4, 3), "x", 'x', new ItemStack(ModBlocks.wood_stripped, 1, 3));
                addShapedRecipe(new ItemStack(Blocks.planks, 4, 4), "x", 'x', new ItemStack(ModBlocks.wood2_stripped, 1, 0));
                addShapedRecipe(new ItemStack(Blocks.planks, 4, 5), "x", 'x', new ItemStack(ModBlocks.wood2_stripped, 1, 1));
            }
        }

    }

    private static void addShapedRecipe(ItemStack output, Object... objects) {
        GameRegistry.addRecipe(new ShapedOreRecipe(output, objects));
    }

    private static void addShapelessRecipe(ItemStack output, Object... objects) {
        GameRegistry.addRecipe(new ShapelessOreRecipe(output, objects));
    }

    private static void removeFirstRecipeFor(Block block) {
        removeFirstRecipeFor(Item.getItemFromBlock(block));
    }

    private static void removeFirstRecipeFor(Item item) {
        for (Object recipe : CraftingManager.getInstance().getRecipeList())
            if (recipe != null) {
                ItemStack stack = ((IRecipe) recipe).getRecipeOutput();
                if (stack != null && stack.getItem() == item) {
                    CraftingManager.getInstance().getRecipeList().remove(recipe);
                    return;
                }
            }
    }
}