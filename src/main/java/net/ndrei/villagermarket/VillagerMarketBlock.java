package net.ndrei.villagermarket;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.ShapedOreRecipe;

/**
 * Created by CF on 2017-02-21.
 */
public class VillagerMarketBlock extends Block {
    public VillagerMarketBlock() {
        super(Material.WOOD, MapColor.BROWN);
    }

    void register() {
        this.setRegistryName(VillagerMarketMod.MODID, "villager_market");
        this.setUnlocalizedName(VillagerMarketMod.MODID + "_villager_market");
        this.setHarvestLevel("axe", 0);
        this.setHardness(3.0f);

        GameRegistry.register(this);
        GameRegistry.register(new ItemBlock(this), this.getRegistryName());

        CraftingManager.getInstance().addRecipe(new ShapedOreRecipe(new ItemStack(Item.getItemFromBlock(this)),
                "xxx", "xex", "xxx",
                'x', Blocks.PLANKS,
                'e', Blocks.EMERALD_BLOCK));
    }

    @SideOnly(Side.CLIENT)
    void registerRenderer() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this)
                , 0
                , new ModelResourceLocation(this.getRegistryName(), "inventory")
        );
    }

    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            playerIn.openGui(VillagerMarketMod.instance, 5, worldIn, pos.getX(), pos.getY(), pos.getZ());
        }

//        if (!worldIn.isRemote && (playerIn instanceof EntityPlayerMP)) {
//            VillagerMarketContainer container = new VillagerMarketContainer(worldIn, pos, playerIn);
//            NBTTagCompound message = container.getNBTForMessage();
//
//            VillagerMarketMod.sendMessageToClient(message, (EntityPlayerMP) playerIn);
//        }

        return true;
    }
}
