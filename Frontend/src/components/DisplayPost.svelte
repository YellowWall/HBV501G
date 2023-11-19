<script>
    import {Card} from 'flowbite-svelte';
    export let title;
    export let text;
    export let username;
    export let id;
    let hrefpointer = "/forum/viewThread?id="+id;
</script>
<main>
    <Card class="sky" href={hrefpointer}>
        <h5 class="mb-2 text-2xl font-bold tracking-tight text-gray-900 dark:text-black">{title}</h5>
        <h1 class="mb-2 text-l font-bold tracking-tight text-gray-900 dark:text-black">{username}</h1>
        <p class="font-normal text-gray-700 dark:text-gray-900 leading-tight">{text}</p>
    </Card>
</main>