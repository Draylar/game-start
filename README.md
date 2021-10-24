# Game Start

Game Start is a mod for server owners, mod-pack creators, and developers that assists with giving the Player loot & sending them messages on first and recurrent joins.

### For Server Owners & Modpack Creators

#### First Join Messages

Game Start provides users with a config file for specifying join messages, and a Loot Table for first-join loot.

The config file can be found at `config/gamestart.json5`:
```json5
{ 
	// The message to send a player when they first join the server.
	"firstJoinPersonalMessage": [ 
		""
	],
	// The message to send to all players when a player first joins the server.
	"firstJoinBroadcastMessage": [ 
		""
	],
	// The message to send a player when they re-join the server (not a new player).
	"returningJoinPersonalMessage": [ 
		""
	],
	// The message to send to all players when a player re-joins the server (not a new player).
	"returningJoinBroadcastMessage": [ 
		""
	],
	// Whether the first join broadcast should also be sent to the player who joined.
	"sendFirstJoinBroadcastToTargetPlayer": false,
	// Whether the returning join broadcast should also be sent to the player who joined.
	"sendReturningJoinBroadcastToTargetPlayer": false
}
```

`firstJoinMessage` is a String array representing the message to send a Player when they *first* join the server.
`returningJoinMessage` is sent when the player re-joins the server, but not on their first join.

`firstJoinBroadcastMessage` is a String array representing the message to send to *all* players on the server when a player *first* joins the server.
`returningJoinBroadcastMessage` is a String array representing the message to send to *all* players on the server when a player re-joins the server, but not on their first join.

In both cases, the phrase `{player}` will be replaced with the given player's username:

```json
"firstJoinMessage": [ 
		"Welcome to the server, {player}!"
]
```

![](https://i.imgur.com/aw7sr3K.png)

#### First Join Loot Table

Game Start also provides users with a Loot Table that is given to players when they first join the server.
To use it, override the file using a datapack at `data/gamestart/loot_tables/first_join.json`.
Here is a simple table that gives players an apple when they first join:

```json
{
  "pools": [
    {
      "rolls": 1,
      "entries": [
        {
          "type": "item",
          "weight": 1,
          "name": "minecraft:apple"
        }
      ]
    }
  ]
}
```

### License
Game Start is licensed under the **public domain** license CC0-1.0. 
You are free to do whatever you want with the mod, code, and assets in this repository.
