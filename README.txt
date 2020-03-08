Everything works in my project. The one small "change" I made for the main part of the project relative to the rubric, is that 
the rubric says: "Each time the user loads the RollActivity the activity rolls the dice" and I twitched this just a little 
bit; whenever the user goes from the MainActivity to the RollActivity dice are rolled, but if the user goes to the 
StatsActivity and clicks cancel, it will bring him back to the RollActivity but the dice will not roll, instead a message "you 
may now reroll" is displayed, and if the user clicks on reroll the same number of dice and sides that origianlly came from the 
main are rolled. 

In terms of the creative portion I implemented a "general randomizer" where the user can input any number of things (between 1 
and 15 things), for example restaurants, movies, places to travel to, etc. and the app will randomly select one and display it 
for the user. To do this I made a different landing screen (called SelectionActivity) which can take you to roll dice (core 
project in the MainActivity) or to a separate activity called PersonalRandomizer. This activity has a box for fragments where 
initially the user must input how many objects or items he/she can choose from (between 1 and 15), the next fragment prompts 
the user for the items, and once the number of items the user specified has been inputed a separate fragment displays the 
randomly chosen option from their input (and "flashes" it on the screen). There are 3 pieces of data that flow between the 
fragments and activity:
1) When the user inputs the number of options they have into the fragment, this gets sent to the activity and then gets sent 
to the fragment where the user inputs the options
2) A list of strings is made from the options inputed by the user and this list (when complete) is sent back to the activity 
where we "calculate" the random option that will be displayed.
3) This "winning" string is then sent to the final fragment to be displayed in the "fragment box".
The PersonalRandomizer has a button below the fragment box which will just take you back to the SelectionActivity, it shows 
"cancel" when the user is typing the numbers or input, and shows "home" once the random option has been displayed.
