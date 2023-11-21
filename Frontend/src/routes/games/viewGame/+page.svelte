<script>
    import DisplayGame from "../../../components/DisplayGame.svelte";
    import DisplayHole from "../../../components/DisplayHole.svelte";
    import {page} from '$app/stores';

    const backendRoute = "http://localhost:8080/"
    async function fetchGame(){
        const urlParams = new URLSearchParams($page.url.search);
        const id = urlParams.get('id');
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
        return json;
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
        return json;
    }
</script>
<main>
    {#await fetchGame()}
    <p>loading</p>
    {:then json} 
    <svelte:component this={DisplayGame} {...json.data}/> 
    {#await fetchHoles()}
    <p>loading holes</p>
    {:then json}
    {#each json.data as hole}
        <svelte:component this={DisplayHole} {...hole}/>
    {/each}
    {/await}

    {/await}
    <p>Not Yet fully Implemented</p>
</main>