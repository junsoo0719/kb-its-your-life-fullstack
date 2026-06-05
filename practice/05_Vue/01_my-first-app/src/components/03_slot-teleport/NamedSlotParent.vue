<template>
  <div>
    <NamedSlotChild>
      <template v-slot:top>
        <h2>여기는 Top 으로 보낼 친구들입니다</h2>
      </template>
      <template #bot>
        <h2>여기는 Bot 으로 보낼 친구들입니다</h2>
      </template>
    </NamedSlotChild>
  </div>
</template>

<script>
import NamedSlotChild from './NamedSlotChild.vue';

export default {
  components: {
    NamedSlotChild,
  },
};
</script>
