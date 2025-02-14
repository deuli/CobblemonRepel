![Cobblemon Repel](https://cdn.modrinth.com/data/cached_images/c649f660c6b6cd03ae0af446d3711a470eaeaba0.png)

(Available on [Modrinth](https://modrinth.com/mod/cobblemon-repel) and [CurseForge](https://legacy.curseforge.com/minecraft/mc-mods/cobblemon-repel))

Cobblemon Repel is a Fabric addon to [Cobblemon](https://modrinth.com/mod/cobblemon) that adds 3 new blocks that prevent Pokémon spawns in a configurable radius. This mod is completely server-side and can be added to the server with players still able to join even without the mod installed, thanks to [Polymer](https://modrinth.com/mod/polymer). It can still be used in singleplayer however.

![Repel](https://cdn.modrinth.com/data/cached_images/282593a72078a8f84619b6314b1c5e40b780b4fe.png) ![Super Repel](https://cdn.modrinth.com/data/cached_images/32b7d4ddb27bed7370aa420ad4aefdf3a220f863.png) ![Max Repel](https://cdn.modrinth.com/data/cached_images/b83855a3f9787a95566ec7c4d199b9d44332cc87.png)

The Repel comes in three variants: regular, Super and Max. The regular Repel stops Pokémon spawns in a 32 block radius around it, while Super and Max double and triple the range respectively.



<details>
<summary>Recipes</summary>

![Repel Recipe](https://cdn.modrinth.com/data/cached_images/13318d6d017bc9db6ba8816afe721a2b7a3f7ccc.png)![Super Repel Recipe](https://cdn.modrinth.com/data/cached_images/88bc9b8db7276b01b4ee4c41cce5fef2c52ea12b.png)![Max Repel Recipe](https://cdn.modrinth.com/data/cached_images/66a7a0bde036f68aece8ac2b88498623770a656d.png)

Any Apricorn can be used, any Cobblemon Berry can be used, any Experience Candy can be used, any Evolution Item can be used

</details>



## Configuration
The range of each Repel can be configured with the `/gamerule` command. Setting their value to 0 will disable the respective Repel.
- `repelRange` - The base range used for regular Repels, other Repels use this with a multiplier, can be a value between 0 and 512, default is 32
- `superRepelRangeMultipler` - The multiplier for the Super Repels, can be a value between 0 and 10, default is 2
- `maxRepelRangeMultipler` - The multiplier for the Max Repels, can be a value between 0 and 10, default is 3

## Mod Support
Cobblemon Repel has support for [Cobblemon Spawn Notification](https://modrinth.com/mod/cobblemon-spawn-notification), where it disables the broadcast if the spawned Pokémon was caught by a Repel.

## (Neo)Forge?
No, because [Polymer](https://modrinth.com/mod/polymer) is currently Fabric only.