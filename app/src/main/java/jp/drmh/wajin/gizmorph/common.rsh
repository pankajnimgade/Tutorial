#pragma once 

static inline uint16_t bit_reverse16(uint16_t x)
{
    x = (x >> 8) | (x << 8);
    x = ((x & 0xF0F0) >> 4) | ((x & 0x0F0F) << 4);
    x = ((x & 0xCCCC) >> 2) | ((x & 0x3333) << 2);
    return ((x & 0xAAAA) >> 1) | ((x & 0x5555) << 1);
}
/*- End of function --------------------------------------------------------*/

static inline uint32_t bit_reverse32(uint32_t x)
{
    x = (x >> 16) | (x << 16);
    x = ((x & 0xFF00FF00) >> 8) | ((x & 0x00FF00FF) << 8);
    x = ((x & 0xF0F0F0F0) >> 4) | ((x & 0x0F0F0F0F) << 4);
    x = ((x & 0xCCCCCCCC) >> 2) | ((x & 0x33333333) << 2);
    return ((x & 0xAAAAAAAA) >> 1) | ((x & 0x55555555) << 1);
}