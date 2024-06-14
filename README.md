# mds-insight

## Descriere
Aplicatia are doua sectiuni, cea de minigames si cea de joc multiplayer. In sectiunea minigames, utilizatorii gasesc 2048, X/0 si minesweeper. Jocul multiplayer este un joc de tip trivia, hostul introduce intrebarile si raspunsurile, iar playerii raspund la acestea, putand sa-si vada si scorul pe parcursul jocului.

## Backlog si user stories: 
![image](https://github.com/L-o-rd/mds-insight/assets/116594293/dc5553ac-b81e-4e7e-91d6-9b9e2290e661)
![Screenshot_84](https://github.com/L-o-rd/mds-insight/assets/128260253/8977db24-b538-4c2f-9e64-a9a65efc7b0f)


## Diagrama UML (state UML)
![Untitled (2)](https://github.com/L-o-rd/mds-insight/assets/116594293/e38eb637-d930-4f1f-aac4-1f486afb7ead)


## Source control
Proiectul se afla in totalitate pe github, fiind impartit in res, pentru partea de resurse, si src/com.insight care contine sursele pentru frontend/backend.

## Design pattern
Am fost folosit:
  * Singleton, pentru conexiunea cu baza de date (Mysql), in clasa Database.
   ![WhatsApp Image 2024-06-14 at 12 19 42_02714a16](https://github.com/L-o-rd/mds-insight/assets/116028853/84081110-da63-4226-9b77-d691dd0f6ce0)
  * Clase utilitare (statice).
   ![WhatsApp Image 2024-06-14 at 12 20 38_98b9d325](https://github.com/L-o-rd/mds-insight/assets/116028853/bf3d713c-af26-40ec-b893-55f169b71426)
  * State Pattern pentru aplicatia principala.
   ![WhatsApp Image 2024-06-14 at 12 21 08_13d9e109](https://github.com/L-o-rd/mds-insight/assets/116028853/63734bd8-eea8-43bb-a873-bf7cec079897)

## Folosirea unui AI & Comentarii cod
![Screenshot_80](https://github.com/L-o-rd/mds-insight/assets/128260253/2b1b27bc-7515-4321-86d8-1102b06bfaa6)
![Screenshot_81](https://github.com/L-o-rd/mds-insight/assets/128260253/4991d426-1428-4672-8fa1-db387b8bdf5f)
![Screenshot_82](https://github.com/L-o-rd/mds-insight/assets/128260253/5872f41f-8185-4285-b7b1-02ab0c013a63)

## Teste automate
Am incercat sa integram teste automate in branch-ul unit-testing, dar compilarea claselor java nu se completeaza.
![Screenshot_83](https://github.com/L-o-rd/mds-insight/assets/128260253/2e263aed-10c9-4e13-974e-9b7af41d0d77)

## Demo

Part 1/2 https://youtu.be/j9oZbsiAGlI
Part 2/2 https://youtu.be/fFTRj1cqJw4

