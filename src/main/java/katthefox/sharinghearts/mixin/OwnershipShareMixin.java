package katthefox.sharinghearts.mixin;

import de.dafuqs.spectrum.blocks.chests.HeartboundChestBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.scoreboard.AbstractTeam;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(HeartboundChestBlockEntity.class)
public class OwnershipShareMixin {
	@Inject(at = @At("RETURN"), method = "checkUnlocked", cancellable = true)
	private void checkUnlockedMixin(PlayerEntity player, CallbackInfoReturnable<Boolean> info) {
		AbstractTeam team = player.getScoreboardTeam();
		if (team != null) {
			info.setReturnValue(
					team.getPlayerList().contains(((HeartboundChestBlockEntity) (Object) this).getOwnerName()));
		}
	}
}