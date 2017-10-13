Feature: simulate a Game

Scenario: simulate a Game between two Teams
Given a Team named Team1
And a Team named Team2
When do simulation of a Game
And Team1 being the hometeam
And Team2 being the roadteam
Then there is a Result of the Game