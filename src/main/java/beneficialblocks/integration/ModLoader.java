package beneficialblocks.integration;

import beneficialblocks.setup.Reference;
import cpw.mods.fml.common.Loader;

public enum ModLoader
{
	BB(Reference.MODID, ""),
	TE("ThermalExpansion", ".te"),
	MFR("MineFactoryReloaded", ".mfr"),
	EIO("EnderIO", ".eio");
	
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
