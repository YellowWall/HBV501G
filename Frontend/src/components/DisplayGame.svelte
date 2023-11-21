<script>
    import {Card} from 'flowbite-svelte';
    export let fieldName;
    export let score;
    export let username;
    export let gameDate;
    export let id;
    let hrefpointer = "/games/viewGame?id="+id;
</script>
<main>
    <Card class="sky" href={hrefpointer}>
        <h5 class="mb-2 text-2xl font-bold tracking-tight text-gray-900 dark:text-black">{fieldName}</h5>
        <h1 class="mb-2 text-l font-bold tracking-tight text-gray-900 dark:text-black">{username}</h1>
        <p class="font-normal text-gray-700 dark:text-gray-900 leading-tight">{gameDate}</p>
        <p class="font-normal text-gray-700 dark:text-gray-900 leading-tight">{score}</p>
    </Card>
</main>