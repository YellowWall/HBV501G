<script>
    import { createEventDispatcher, onMount } from 'svelte';
    import {browser} from '$app/environment';
    import {goto} from '$app/navigation';
    import { Table, TableBody, TableBodyCell, TableBodyRow, TableHead, TableHeadCell } from 'flowbite-svelte';
    let backendRoute = 'https://hbv501g-backend.onrender.com';
    let selected =-1;
    let fields = [];
    let failed = false;


    //sækir velli
    onMount(async function getFields(){
        const res = await fetch(
            backendRoute + '/field/fields',
            {method: 'GET',
            headers: {"Content-Type": "application/json",
            "Authorization": "Bearer "+window.sessionStorage.getItem('authenticatorTocen')}
            }
        )
        const json = await res.json();
        if (json.success) {
            fields = json.data;
        }
    });

    //vistar upplýsingar úr töflunni sem nýjan leik
    async function saveGame(){
        if(selected == -1) return;
        const res = await fetch(
            backendRoute + '/game/gameformpost',
            {method: 'POST',
                headers: {"Content-Type": "application/json",
                        "Authorization": "Bearer "+window.sessionStorage.getItem('authenticatorTocen')},
                body: JSON.stringify({
                    username:window.sessionStorage.getItem("Username"),
                    field:selected
                })
            }
        )
        const json = await res.json();
        console.log(json);
        if(json.success){  
            failed = false;
            goto('/games/viewGame?id='+json.data.id);
        }else{
            failed = true;
        }
    }

</script>

<h1>new game</h1>
{#if failed}
<p>failed to make new game</p>
{/if}
<select bind:value={selected}>
    {#each fields as option}<option value={option.id}>{option.name}</option>{/each}
</select>
<button on:click={saveGame}>make game</button>