package beneficialblocks.integration;

import beneficialblocks.setup.Reference;
import cpw.mods.fml.common.Loader;

public enum ModLoader
{
	BENEFICIAL_BLOCKS(Reference.MODID, ""),
	THERMAL_EXPANSION("ThermalExpansion", ".te"),
	MINEFACTORY_RELOADED("MineFactoryReloaded", ".mfr"),
	ENDER_IO("EnderIO", ".eio");
	
	public final String modid;
	public final String suffix;
	
	private ModLoader(String modid, String suffix)
	{
		this.modid = modid;
		this.suffix = suffix;
	}
	
	public boolean isLoaded()
	{
		return Loader.isModLoaded(this.modid);
	}
}
