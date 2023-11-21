<script>
    import {Card} from 'flowbite-svelte';
    export let yeets;

    export let username;

    export let id;
    //let hrefpointer = "/games/viewGame?id="+id;
</script>
<main>
    <Card class="sky">
        <h2 >{yeets}</h2>
        <h1 class="mb-2 text-l font-bold tracking-tight text-gray-900 dark:text-black">{username}</h1>
    </Card>
</main>