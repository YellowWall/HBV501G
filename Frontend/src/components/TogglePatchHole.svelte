<script>
	import { Button } from "flowbite-svelte";
    import {browser} from '$app/environment';
	import PostHoleForm from "./PostHoleForm.svelte";
    let username;
    if(browser){
        username = window.sessionStorage.getItem("Username");
    }
    export let hole;
    let toggle = false;
</script>
<main>
{#if hole.username == username}
<Button on:click={()=>(toggle = !toggle)}> Edit</Button>
{#if toggle}
<svelte:component this={PostHoleForm} {...hole}/>
{/if}
{/if}
</main>