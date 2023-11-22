<script>
    import DisplayGame from "../../../components/DisplayGame.svelte";
    import DisplayHole from "../../../components/DisplayHole.svelte";
    import {page} from '$app/stores';
    import {browser} from '$app/environment';
	import PostHoleForm from "../../../components/postHoleForm.svelte";
    import TogglePatchHole from "../../../components/formutils/TogglePatchHole.svelte";
	import Header from "../../../components/Header.svelte";
	import { Button } from "flowbite-svelte";

    const backendRoute = "http://localhost:8080/"
    const urlParams = new URLSearchParams($page.url.search);
    const id = urlParams.get('id');
    let username;
    let gameId = id;
    if(browser){
        username = window.sessionStorage.getItem('Username');
    }
    async function fetchGame(){
        const res = await fetch(
            backendRoute+"game/displaygame/"+id,
            {
                method: 'GET',
                headers: {"Content-Type": "application/json",
                "Authorization": "Bearer " + window.sessionStorage.getItem('authenticatorTocen')}
            }
        )
        const json = await res.json();
        console.log(json);
        
        return json.data;
    }
    async function fetchHoles(){
        const urlParams = new URLSearchParams($page.url.search);
        const id = urlParams.get('id');
        const res = await fetch(
            backendRoute+ "hole/displayholes/"+id,
            {
                method: 'GET',
                headers: {"Content-Type": "application/json",
                "Authorization": "Bearer " + window.sessionStorage.getItem('authenticatorTocen')}
            }
        )
        const json = await res.json();
        console.log(json);
        return json.data;
    }
</script>
<main>
    <Header/>
    {#await fetchGame()}
    <p>loading</p>
    {:then game} 
    <svelte:component this={DisplayGame} {...game}/> 
    {#await fetchHoles()}
    <p>loading holes</p>
    {:then holes}
    {#each holes as hole}
        <svelte:component this={DisplayHole} {...hole}/>
        <svelte:component this={TogglePatchHole} {hole}/>
    {/each}
    {/await}
    {#if game.username == username}
        <svelte:component this={PostHoleForm} {gameId}/>
    {/if}
    {/await}
    <p>Not Yet fully Implemented</p>
</main>