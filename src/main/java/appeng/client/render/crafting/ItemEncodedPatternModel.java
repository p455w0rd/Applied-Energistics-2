package appeng.client.render.crafting;


import java.util.Collection;
import java.util.Collections;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableMap;

import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.IPerspectiveAwareModel;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.common.model.IModelState;
import net.minecraftforge.common.model.TRSRTransformation;

import appeng.core.AppEng;


/**
 * Simple model for the encoded pattern built-in baked model.
 */
class ItemEncodedPatternModel implements IModel
{

	private static final ResourceLocation BASE_MODEL = new ResourceLocation( AppEng.MOD_ID, "item/encoded_pattern" );

	@Override
	public Collection<ResourceLocation> getDependencies()
	{
		return Collections.singletonList( BASE_MODEL );
	}

	@Override
	public Collection<ResourceLocation> getTextures()
	{
		return Collections.emptyList();
	}

	@Override
	public IBakedModel bake( IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter )
	{
		IBakedModel baseModel;
		try
		{
			baseModel = ModelLoaderRegistry.getModel( BASE_MODEL ).bake( state, format, bakedTextureGetter );
		}
		catch( Exception e )
		{
			throw new RuntimeException( e );
		}

		ImmutableMap<ItemCameraTransforms.TransformType, TRSRTransformation> transforms = IPerspectiveAwareModel.MapWrapper.getTransforms(state);

		return new ItemEncodedPatternBakedModel( baseModel, transforms );
	}

	@Override
	public IModelState getDefaultState()
	{
		return TRSRTransformation.identity();
	}
}