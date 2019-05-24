package net.peng1104;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import me.clip.placeholderapi.PlaceholderAPIPlugin;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;

import net.sacredlabyrinth.phaed.simpleclans.Clan;
import net.sacredlabyrinth.phaed.simpleclans.ClanPlayer;
import net.sacredlabyrinth.phaed.simpleclans.SimpleClans;
import net.sacredlabyrinth.phaed.simpleclans.managers.ClanManager;

public class SimpleClansExpansion extends PlaceholderExpansion {

	private ClanManager clanManager;
	
	@Override
	public String getAuthor() {
		return "Peng1104";
	}

	@Override
	public String getIdentifier() {
		return "simpleclans";
	}

	@Override
	public String getVersion() {
		return "2.0.3";
	}
	
	@Override
	public String getPlugin() {
		return "SimpleClans";
	}
	
	@Override
	public boolean register() {
		boolean result = false;
		
		SimpleClans simpleClans = (SimpleClans) Bukkit.getPluginManager().getPlugin(getPlugin());
		
		if (simpleClans != null) {
			if (!simpleClans.isEnabled()) {
				Bukkit.getPluginManager().enablePlugin(simpleClans);
			}
			clanManager = SimpleClans.getInstance().getClanManager();
			
			if (clanManager != null) {
				result = super.register();
			}
		}
		return result;
	}
	
	@Override
	public String onRequest(OfflinePlayer p, String identifier) {
		if (p == null || p.getUniqueId() == null) {
			return "";
		}
		ClanPlayer player = clanManager.getClanPlayer(p.getUniqueId());
		
		if (player == null) {
			return "";
		}
		switch (identifier) {
			case "neutral_kills": {
				return String.valueOf(player.getNeutralKills());
			}
			case "rival_kills": {
				return String.valueOf(player.getRivalKills());
			}
			case "civilian_kills": {
				return String.valueOf(player.getCivilianKills());
			}
			case "total_kills": {
				return String.valueOf(player.getNeutralKills() + player.getRivalKills() + player.getCivilianKills());
			}
			case "weighted_kills": {
				return String.valueOf(player.getWeightedKills());
			}
			case "deaths": {
				return String.valueOf(player.getDeaths());
			}
			case "kdr": {
				return String.valueOf(player.getKDR());
			}
			case "in_clan": {
				return (player.getClan() != null) ? PlaceholderAPIPlugin.booleanTrue() : PlaceholderAPIPlugin.booleanFalse();
			}
			case "is_leader": {
				return player.isLeader() ? PlaceholderAPIPlugin.booleanTrue() : PlaceholderAPIPlugin.booleanFalse();
			}
			case "is_trusted": {
				return (!player.isLeader() && player.isTrusted()) ? PlaceholderAPIPlugin.booleanTrue() : PlaceholderAPIPlugin.booleanFalse();
			}
			case "is_member": {
				return (!player.isTrusted() && !player.isLeader() && player.getClan() != null) ? PlaceholderAPIPlugin.booleanTrue() : PlaceholderAPIPlugin.booleanFalse();
			}
			case "is_bb_enabled": {
				return player.isBbEnabled() ? PlaceholderAPIPlugin.booleanTrue() : PlaceholderAPIPlugin.booleanFalse();
			}
			case "is_usechatshortcut": {
				return player.isUseChatShortcut() ? PlaceholderAPIPlugin.booleanTrue() : PlaceholderAPIPlugin.booleanFalse();
			}
			case "is_allychat": {
				return player.isAllyChat() ? PlaceholderAPIPlugin.booleanTrue() : PlaceholderAPIPlugin.booleanFalse();
			}
			case "is_clanchat": {
				return player.isClanChat() ? PlaceholderAPIPlugin.booleanTrue() : PlaceholderAPIPlugin.booleanFalse();
			}
			case "is_globalchat": {
				return player.isGlobalChat() ? PlaceholderAPIPlugin.booleanTrue() : PlaceholderAPIPlugin.booleanFalse();
			}
			case "is_cape_enabled": {
				return player.isCapeEnabled() ? PlaceholderAPIPlugin.booleanTrue() : PlaceholderAPIPlugin.booleanFalse();
			}
			case "is_tag_enabled": {
				return player.isTagEnabled() ? PlaceholderAPIPlugin.booleanTrue() : PlaceholderAPIPlugin.booleanFalse();
			}
			case "is_friendlyfire_on": {
				return player.isFriendlyFire() ? PlaceholderAPIPlugin.booleanTrue() : PlaceholderAPIPlugin.booleanFalse();
			}
			case "is_muted": {
				return player.isMuted() ? PlaceholderAPIPlugin.booleanTrue() : PlaceholderAPIPlugin.booleanFalse();
			}
			case "is_mutedally": {
				return player.isMutedAlly() ? PlaceholderAPIPlugin.booleanTrue() : PlaceholderAPIPlugin.booleanFalse();
			}
			case "join_date": {
				return player.getJoinDateString();
			}
			case "inactive_days": {
				return String.valueOf(player.getInactiveDays());
			}
			case "lastseen": {
				return player.getLastSeenString();
			}
			case "lastseendays": {
				return player.getLastSeenDaysString();
			}
			case "tag": {
				return player.getTag();
			}
			case "tag_label": {
				return player.getTagLabel();
			}
			case "rank": {
				return player.getRank();
			}
			default:
				break;
		}
		if (player.getClan() == null) {
			return "";
		}
		Clan c = player.getClan();
		
		switch (identifier) {
			case "clan_total_neutral": {
				return String.valueOf(c.getTotalNeutral());
			}
			case "clan_total_civilian": {
				return String.valueOf(c.getTotalCivilian());
			}
			case "clan_total_rival": {
				return String.valueOf(c.getTotalRival());
			}
			case "clan_total_kills": {
				return String.valueOf(c.getTotalRival() + c.getTotalNeutral() + c.getTotalCivilian());
			}
			case "clan_total_deaths": {
				return String.valueOf(c.getTotalDeaths());
			}
			case "clan_total_kdr": {
				return String.valueOf(c.getTotalKDR());
			}
			case "clan_average_wk": {
				return String.valueOf(c.getAverageWK());
			}
			case "clan_leader_size": {
				return String.valueOf(c.getLeaders().size());
			}
			case "clan_balance": {
				return String.valueOf(c.getBalance());
			}
			case "clan_allow_withdraw": {
				return c.isAllowWithdraw() ? PlaceholderAPIPlugin.booleanTrue() : PlaceholderAPIPlugin.booleanFalse();
			}
			case "clan_allow_deposit": {
				return c.isAllowDeposit() ? PlaceholderAPIPlugin.booleanTrue() : PlaceholderAPIPlugin.booleanFalse();
			}
			case "clan_size": {
				return String.valueOf(c.getSize());
			}
			case "clan_name": {
				return c.getName();
			}
			case "clan_color_tag": {
				return c.getColorTag();
			}
			case "clan_tag_label": {
				return c.getTagLabel();
			}
			case "clan_tag": {
				return c.getTag();
			}
			case "clan_founded": {
				return c.getFoundedString();
			}
			case "clan_friendly_fire": {
				return c.isFriendlyFire() ? PlaceholderAPIPlugin.booleanTrue() : PlaceholderAPIPlugin.booleanFalse();
			}
			case "clan_is_unrivable": {
				return c.isUnrivable() ? PlaceholderAPIPlugin.booleanTrue() : PlaceholderAPIPlugin.booleanFalse();
			}
			case "clan_is_anyonline": {
				return c.isAnyOnline() ? PlaceholderAPIPlugin.booleanTrue() : PlaceholderAPIPlugin.booleanFalse();
			}
			case "clan_is_verified": {
				return c.isVerified() ? PlaceholderAPIPlugin.booleanTrue() : PlaceholderAPIPlugin.booleanFalse();
			}
			case "clan_capeurl": {
				return c.getCapeUrl();
			}
			case "clan_inactivedays": {
				return String.valueOf(c.getInactiveDays());
			}
			case "clan_onlinemembers_count": {
				return String.valueOf(c.getOnlineMembers().size());
			}
			case "clan_allies_count": {
				return String.valueOf(c.getAllies().size());
			}
			case "clan_rivals_count": {
				return String.valueOf(c.getRivals().size());
			}
			default:
				break;
		}
		return null;
	}
}